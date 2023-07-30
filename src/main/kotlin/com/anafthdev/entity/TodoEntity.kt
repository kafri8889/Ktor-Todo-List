package com.anafthdev.entity

import com.anafthdev.model.ExposedTodo
import com.anafthdev.model.db.SubTodoTable
import com.anafthdev.model.db.TodoTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SizedIterable

class TodoEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object: IntEntityClass<TodoEntity>(TodoTable)

    var categoryId by TodoTable.categoryId
    var title by TodoTable.title
    var description by TodoTable.description
    var createdAt by TodoTable.createdAt
    var finished by TodoTable.finished
    var userId by UserEntity referencedOn TodoTable.userId

    val subTodo: SizedIterable<SubTodoEntity>? by SubTodoEntity referrersOn SubTodoTable.todoId

    fun toExposedTodo(): ExposedTodo = ExposedTodo(
        id = id.value,
        userId = userId.id.value,
        categoryId = categoryId,
        title = title,
        description = description,
        createdAt = createdAt,
        finished = finished,
        subTodo = subTodo?.map { it.toExposedSubTodo() } ?: emptyList()
    )
}