package com.anafthdev.routing

import com.anafthdev.common.checkId
import com.anafthdev.model.ExposedUser
import com.anafthdev.model.response.ErrorResponse
import com.anafthdev.model.response.SuccessResponse
import com.anafthdev.services.UserService
import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.userRouting(userService: UserService) {
    routing {
        post("/user") {
            val user = Gson().fromJson(call.receiveText(), ExposedUser::class.java)
            val exposedUser = userService.insert(user)
            call.respondText(
                contentType = ContentType.Application.Json,
                text = Gson().toJson(SuccessResponse(HttpStatusCode.Created.value, "User created", exposedUser))
            )
        }

        get("/user") {
            val email = call.request.queryParameters["email"]
            val password = call.request.queryParameters["password"]
            val include = call.request.queryParameters["include"]?.toBoolean()

            if (email != null && password != null) {
                val exposedUser = userService.getByEmailAndPassword(email, password, include ?: false)
                if (exposedUser != null) {
                    call.respondText(
                        contentType = ContentType.Application.Json,
                        text = Gson().toJson(SuccessResponse(HttpStatusCode.OK.value, "User found", exposedUser))
                    )
                } else call.respondText(
                    contentType = ContentType.Application.Json,
                    text = Gson().toJson(ErrorResponse(HttpStatusCode.NotFound.value, "User not found"))
                )
            } else checkId(null) {}
        }

        get("/user/{id}") {
            val id = call.parameters["id"]?.toInt()
            val include = call.request.queryParameters["include"]?.toBoolean()
            checkId(id) {
                val exposedUser = userService.get(id!!, include ?: false)
                if (exposedUser != null) {
                    call.respondText(
                        contentType = ContentType.Application.Json,
                        text = Gson().toJson(SuccessResponse(HttpStatusCode.OK.value, "User found", exposedUser))
                    )
                } else call.respondText(
                    contentType = ContentType.Application.Json,
                    text = Gson().toJson(ErrorResponse(HttpStatusCode.NotFound.value, "User not found"))
                )
            }
        }

        put("/user/{id}") {
            val id = call.parameters["id"]?.toInt()
            checkId(id) {
                val user = Gson().fromJson(call.receiveText(), ExposedUser::class.java)
                val exposedUser = userService.update(id!!, user)
                call.respondText(
                    contentType = ContentType.Application.Json,
                    text = Gson().toJson(SuccessResponse(HttpStatusCode.OK.value, "User updated", exposedUser))
                )
            }
        }

        delete("/user/{id}") {
            val id = call.parameters["id"]?.toInt()
            checkId(id) {
                val exposedUser = userService.delete(id!!)
                call.respondText(
                    contentType = ContentType.Application.Json,
                    text = Gson().toJson(SuccessResponse(HttpStatusCode.OK.value, "User deleted", exposedUser))
                )
            }
        }
    }
}
