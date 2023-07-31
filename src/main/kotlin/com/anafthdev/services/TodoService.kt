package com.anafthdev.services

import com.anafthdev.common.dbQuery
import com.anafthdev.entity.TodoEntity
import com.anafthdev.entity.UserEntity
import com.anafthdev.model.ExposedTodo
import com.anafthdev.model.db.TodoTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class TodoService(database: Database) {

    init {
        transaction(database) {
            SchemaUtils.create(TodoTable)
        }
    }

    suspend fun insert(todo: ExposedTodo): ExposedTodo {
        return dbQuery {
            TodoEntity.new {
                categoryId = todo.categoryId
                title = todo.title
                description = todo.description
                createdAt = todo.createdAt
                finished = todo.finished
                userId = UserEntity[todo.userId]
            }.toExposedTodo()
        }
    }

    suspend fun get(id: Int): ExposedTodo? {
        return dbQuery { TodoEntity.findById(id)?.toExposedTodo() }
    }

    suspend fun getByCategoryId(id: Int): List<ExposedTodo> {
        return dbQuery { TodoEntity.find { TodoTable.categoryId eq id }.map { it.toExposedTodo() } }
    }

    suspend fun getByUserId(id: Int): List<ExposedTodo> {
        return dbQuery { TodoEntity.find { TodoTable.userId eq id }.map { it.toExposedTodo() } }
    }

    suspend fun update(id: Int, todo: ExposedTodo) {
        dbQuery {
            TodoTable.update({ TodoTable.id eq id }) {
                it[categoryId] = todo.categoryId
                it[title] = todo.title
                it[description] = todo.description
                it[createdAt] = todo.createdAt
                it[finished] = todo.finished
            }
        }
    }

    suspend fun delete(id: Int) {
        dbQuery {
            TodoTable.deleteWhere { TodoTable.id eq id }
        }
    }

}