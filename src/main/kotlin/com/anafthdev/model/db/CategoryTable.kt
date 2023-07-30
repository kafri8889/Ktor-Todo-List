package com.anafthdev.model.db

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption

object CategoryTable: IntIdTable() {
    val name: Column<String> = varchar("name", 1000000000)

    val userId = reference(
        name = "user_id",
        foreign = UserTable,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.NO_ACTION
    )
}