package com.anafthdev.services

import com.anafthdev.entity.UserEntity
import com.anafthdev.model.ExposedUser
import com.anafthdev.model.db.CategoryTable
import com.anafthdev.model.db.UserTable
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class UserService(private val database: Database) {
    init {
        transaction(database) {
            SchemaUtils.create(UserTable)
            SchemaUtils.create(CategoryTable)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    suspend fun insert(user: ExposedUser): UserEntity {
        return dbQuery {
            UserEntity.new {
                name = user.name
                email = user.email
                password = user.password
            }
        }
    }

    suspend fun get(id: Int): ExposedUser? {
        return dbQuery { UserEntity.findById(id)?.toExposedUser() }
    }

    suspend fun update(id: Int, user: ExposedUser) {
        dbQuery {
            UserTable.update({ UserTable.id eq id }) {
                it[this.id] = user.id
                it[name] = user.name
                it[email] = user.email
                it[password] = user.password
            }
        }
    }

    suspend fun delete(id: Int) {
        dbQuery {
            UserTable.deleteWhere { UserTable.id eq id }
        }
    }
}
