package com.anafthdev.plugins

import com.anafthdev.routing.userRouting
import com.anafthdev.services.UserService
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabases() {
    val database = Database.connect(
        url = "jdbc:h2:file:./build/db",
        user = "root",
        driver = "org.h2.Driver",
        password = ""
    )

    val userService = UserService(database)

    userRouting(userService)
}
