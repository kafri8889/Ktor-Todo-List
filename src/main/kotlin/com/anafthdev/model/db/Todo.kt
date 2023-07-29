package com.anafthdev.model.db

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Todo: Table() {
    val id: Column<Int> = integer("id")
    val userId: Column<Int> = integer("user_id")
    val categoryId: Column<Int> = integer("category_id")
    val title: Column<String> = varchar("title", 1000000000)
    val description: Column<String> = varchar("description", 1000000000)
    val createdAt: Column<Long> = long("created_at")
    val finished: Column<Boolean> = bool("finished")

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id, name = "pk_todo_id")
}