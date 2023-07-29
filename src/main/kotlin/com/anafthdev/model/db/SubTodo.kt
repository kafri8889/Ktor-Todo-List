package com.anafthdev.model.db

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object SubTodo: Table() {
    val id: Column<Int> = integer("id")
    val userId: Column<Int> = integer("user_id")
    val todoId: Column<Int> = integer("todo_id")
    val title: Column<String> = varchar("title", 1000000000)
    val createdAt: Column<Long> = long("created_at")
    val finished: Column<Boolean> = bool("finished")

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id, name = "pk_sub_todo_id")
}
