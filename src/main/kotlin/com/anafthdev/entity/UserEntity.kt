package com.anafthdev.entity

import com.anafthdev.model.ExposedUser
import com.anafthdev.model.db.CategoryTable
import com.anafthdev.model.db.TodoTable
import com.anafthdev.model.db.UserTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SizedIterable

class UserEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object: IntEntityClass<UserEntity>(UserTable)

    var name by UserTable.name
    var email by UserTable.email
    var password by UserTable.password

    val todo: SizedIterable<TodoEntity>? by TodoEntity referrersOn TodoTable.userId
    val categories: SizedIterable<CategoryEntity>? by CategoryEntity referrersOn CategoryTable.userId

    fun toExposedUser(): ExposedUser = ExposedUser(
        id = id.value,
        name = name,
        email = email,
        password = password,
        categories = categories?.map { it.toExposedCategory() } ?: emptyList(),
        todo = todo?.map { it.toExposedTodo() } ?: emptyList()
    )
}