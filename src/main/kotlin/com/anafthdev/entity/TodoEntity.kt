package com.anafthdev.entity

import com.anafthdev.model.db.TodoTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class TodoEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object: IntEntityClass<TodoEntity>(TodoTable)

}