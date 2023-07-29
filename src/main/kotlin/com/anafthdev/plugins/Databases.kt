package com.anafthdev.plugins

import com.anafthdev.model.response.ErrorResponse
import com.anafthdev.model.ExposedUser
import com.anafthdev.model.response.SuccessResponse
import com.anafthdev.services.UserService
import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.*

fun Application.configureDatabases() {
    val database = Database.connect(
            url = "jdbc:h2:file:./build/db",
            user = "root",
            driver = "org.h2.Driver",
            password = ""
        )
    val userService = UserService(database)
    routing {
        // Create user
        post("/users") {
            val user = Gson().fromJson(call.receiveText(), ExposedUser::class.java)
            val id = userService.insert(user)
            call.respondText(Gson().toJson(SuccessResponse(HttpStatusCode.Created.value, "User berhasil dibuat")))
        }
        // Read user
        get("/users/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val user = userService.get(id)
            if (user != null) {
                call.respondText(Gson().toJson(user))
            } else call.respondText(Gson().toJson(ErrorResponse(HttpStatusCode.NotFound.value, "User not found")))
        }
        // Update user
        put("/users/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val user = call.receive<ExposedUser>()
            userService.update(id, user)
            call.respond(HttpStatusCode.OK)
        }
        // Delete user
        delete("/users/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            userService.delete(id)
            call.respond(HttpStatusCode.OK)
        }
    }
}
