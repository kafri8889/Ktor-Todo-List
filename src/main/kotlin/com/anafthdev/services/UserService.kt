package com.anafthdev.services

import com.anafthdev.model.ExposedUser
import com.anafthdev.model.db.User
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

class UserService(private val database: Database) {
    init {
        transaction(database) {
            SchemaUtils.create(User)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    suspend fun insert(user: ExposedUser): Int = dbQuery {
        User.insert {
            it[id] = user.id
            it[name] = user.name
            it[email] = user.email
            it[password] = user.password
        }[User.id]
    }

    suspend fun get(id: Int): ExposedUser? {
        return dbQuery {
            User.select { User.id eq id }
                .map { ExposedUser.fromResultRow(it) }
                .singleOrNull()
        }
    }

    suspend fun update(id: Int, user: ExposedUser) {
        dbQuery {
            User.update({ User.id eq id }) {
                it[this.id] = user.id
                it[name] = user.name
                it[email] = user.email
                it[password] = user.password
            }
        }
    }

    suspend fun delete(id: Int) {
        dbQuery {
            User.deleteWhere { User.id.eq(id) }
        }
    }
}
