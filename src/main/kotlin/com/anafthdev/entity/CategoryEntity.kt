package com.anafthdev.entity

import com.anafthdev.model.db.CategoryTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CategoryEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object: IntEntityClass<CategoryEntity>(CategoryTable)

    var name by CategoryTable.name
    var userId by CategoryTable.userId
}