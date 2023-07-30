package com.anafthdev.entity

import com.anafthdev.model.ExposedUser
import com.anafthdev.model.db.UserTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object: IntEntityClass<UserEntity>(UserTable)

    var name by UserTable.name
    var email by UserTable.email
    var password by UserTable.password

    fun toExposedUser(): ExposedUser = ExposedUser(
        id = id.value,
        name = name,
        email = email,
        password = password
    )
}