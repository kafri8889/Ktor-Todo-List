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
            call.respondText(Gson().toJson(SuccessResponse(HttpStatusCode.Created.value, "User berhasil dibuat", exposedUser)))
        }

        get("/user/{id}") {
            val id = call.parameters["id"]?.toInt()
            checkId(id) {
                val user = userService.get(id!!)
                if (user != null) {
                    call.respondText(Gson().toJson(user))
                } else call.respondText(Gson().toJson(ErrorResponse(HttpStatusCode.NotFound.value, "User not found")))
            }
        }

        put("/user/{id}") {
            val id = call.parameters["id"]?.toInt()
            checkId(id) {
                val user = call.receive<ExposedUser>()
                val exposedUser = userService.update(id!!, user)
                call.respondText(Gson().toJson(SuccessResponse(HttpStatusCode.OK.value, "User berhasil diupdate", exposedUser)))
            }
        }

        delete("/user/{id}") {
            val id = call.parameters["id"]?.toInt()
            checkId(id) {
                val exposedUser = userService.delete(id!!)
                call.respondText(Gson().toJson(SuccessResponse(HttpStatusCode.OK.value, "User berhasil dihapus", exposedUser)))
            }
        }
    }
}
