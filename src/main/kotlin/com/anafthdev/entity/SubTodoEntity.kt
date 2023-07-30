package com.anafthdev.entity

import com.anafthdev.model.ExposedSubTodo
import com.anafthdev.model.db.CategoryTable
import com.anafthdev.model.db.SubTodoTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SubTodoEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object: IntEntityClass<SubTodoEntity>(CategoryTable)

    var title by SubTodoTable.title
    var createdAt by SubTodoTable.createdAt
    var finished by SubTodoTable.finished
    var todoId by TodoEntity referencedOn SubTodoTable.todoId

    fun toExposedSubTodo(): ExposedSubTodo = ExposedSubTodo(
        todoId = todoId.id.value,
        title = title,
        createdAt = createdAt,
        finished = finished
    )
}