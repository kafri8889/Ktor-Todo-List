package com.anafthdev.plugins

import com.anafthdev.routing.categoryRouting
import com.anafthdev.routing.subTodoRouting
import com.anafthdev.routing.todoRouting
import com.anafthdev.routing.userRouting
import com.anafthdev.services.CategoryService
import com.anafthdev.services.SubTodoService
import com.anafthdev.services.TodoService
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
    val todoService = TodoService(database)
    val subTodoService = SubTodoService(database)
    val categoryService = CategoryService(database)

    userRouting(userService)
    todoRouting(todoService)
    subTodoRouting(subTodoService)
    categoryRouting(categoryService)
}
