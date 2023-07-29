package com.anafthdev.model.db

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object User: Table() {
    val id: Column<Int> = integer("id")
    val name: Column<String> = varchar("name", 30)
    val email: Column<String> = varchar("email", 50)
    val password: Column<String> = varchar("password", 50)

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id, name = "pk_user_id")
}
