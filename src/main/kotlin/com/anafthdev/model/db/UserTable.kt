package com.anafthdev.model.db

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object UserTable: IntIdTable() {
    val name: Column<String> = varchar("name", 30)
    val email: Column<String> = varchar("email", 50)
    val password: Column<String> = varchar("password", 50)
}
