package com.anafthdev.model.db

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object CategoryTable: IntIdTable() {
    val name: Column<String> = varchar("name", 1000000000)

    val userId = reference("user_id", UserTable.id)
}