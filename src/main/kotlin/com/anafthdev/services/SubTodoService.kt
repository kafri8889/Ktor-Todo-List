package com.anafthdev.services

import com.anafthdev.common.dbQuery
import com.anafthdev.entity.SubTodoEntity
import com.anafthdev.entity.TodoEntity
import com.anafthdev.model.ExposedSubTodo
import com.anafthdev.model.db.SubTodoTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class SubTodoService(database: Database) {

    init {
        transaction(database) {
            SchemaUtils.create(SubTodoTable)
        }
    }

    suspend fun insert(subTodo: ExposedSubTodo): ExposedSubTodo {
        return dbQuery {
            SubTodoEntity.new {
                title = subTodo.title
                createdAt = subTodo.createdAt
                finished = subTodo.finished
                todoId = TodoEntity[subTodo.todoId]
            }.toExposedSubTodo()
        }
    }

    suspend fun get(id: Int): ExposedSubTodo? {
        return dbQuery { SubTodoEntity.findById(id)?.toExposedSubTodo() }
    }

    suspend fun getByTodoId(id: Int): List<ExposedSubTodo> {
        return dbQuery { SubTodoEntity.find { SubTodoTable.todoId eq id }.map { it.toExposedSubTodo() } }
    }

    suspend fun update(id: Int, subTodo: ExposedSubTodo) {
        dbQuery {
            SubTodoTable.update({ SubTodoTable.id eq id }) {
                it[title] = subTodo.title
                it[createdAt] = subTodo.createdAt
                it[finished] = subTodo.finished
            }
        }
    }

    suspend fun delete(id: Int) {
        dbQuery {
            SubTodoTable.deleteWhere { SubTodoTable.id eq id }
        }
    }

}