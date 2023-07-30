package com.anafthdev.services

import com.anafthdev.common.dbQuery
import com.anafthdev.entity.UserEntity
import com.anafthdev.model.ExposedUser
import com.anafthdev.model.db.UserTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class UserService(database: Database) {

    init {
        transaction(database) {
            SchemaUtils.create(UserTable)
        }
    }

    suspend fun insert(user: ExposedUser): ExposedUser {
        return dbQuery {
            UserEntity.new {
                name = user.name
                email = user.email
                password = user.password
            }.toExposedUser()
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
