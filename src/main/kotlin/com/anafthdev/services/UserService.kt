package com.anafthdev.services

import com.anafthdev.common.dbQuery
import com.anafthdev.entity.UserEntity
import com.anafthdev.model.ExposedUser
import com.anafthdev.model.db.UserTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class UserService(database: Database) {

    init {
        transaction(database) {
            SchemaUtils.create(UserTable)
        }
    }

    /**
     * if [include], ExposedUser will include [ExposedUser.categories] and [ExposedUser.todo]
     */
    suspend fun getByEmailAndPassword(email: String, password: String, include: Boolean = false): ExposedUser? {
        return dbQuery { UserEntity.find { (UserTable.email eq email) and (UserTable.password eq password) }.firstOrNull()?.toExposedUser(include) }
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

    /**
     * if [include], ExposedUser will include [ExposedUser.categories] and [ExposedUser.todo]
     */
    suspend fun get(id: Int, include: Boolean = false): ExposedUser? {
        return dbQuery { UserEntity.findById(id)?.toExposedUser(include) }
    }

    suspend fun update(id: Int, user: ExposedUser) {
        dbQuery {
            UserTable.update({ UserTable.id eq id }) {
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
