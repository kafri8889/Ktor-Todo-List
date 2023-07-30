package com.anafthdev.model.db

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption

object TodoTable: IntIdTable() {
    val categoryId: Column<Int> = integer("category_id")
    val title: Column<String> = varchar("title", 1000000000)
    val description: Column<String> = varchar("description", 1000000000)
    val createdAt: Column<Long> = long("created_at")
    val finished: Column<Boolean> = bool("finished")

    val userId = reference(
        name = "user_id",
        foreign = UserTable,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.NO_ACTION
    )
}