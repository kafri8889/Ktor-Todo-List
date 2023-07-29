package com.anafthdev.model.db

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Category: Table() {
    val id: Column<Int> = integer("id")
    val userId: Column<Int> = integer("user_id")
    val name: Column<String> = varchar("name", 1000000000)

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id, name = "pk_category_id")
}