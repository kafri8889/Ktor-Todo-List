package com.anafthdev.model.db

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object SubTodoTable: IntIdTable() {
    val userId: Column<Int> = integer("user_id")
    val todoId: Column<Int> = integer("todo_id")
    val title: Column<String> = varchar("title", 1000000000)
    val createdAt: Column<Long> = long("created_at")
    val finished: Column<Boolean> = bool("finished")
}
