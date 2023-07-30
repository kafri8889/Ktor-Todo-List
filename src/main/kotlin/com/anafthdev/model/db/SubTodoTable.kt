package com.anafthdev.model.db

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption

object SubTodoTable: IntIdTable() {
    val title: Column<String> = varchar("title", 1000000000)
    val createdAt: Column<Long> = long("created_at")
    val finished: Column<Boolean> = bool("finished")

    val todoId = reference(
        name = "todo_id",
        foreign = TodoTable,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.NO_ACTION
    )
}
