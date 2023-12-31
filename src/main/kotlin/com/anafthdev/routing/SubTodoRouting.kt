package com.anafthdev.routing

import com.anafthdev.common.checkId
import com.anafthdev.model.ExposedSubTodo
import com.anafthdev.model.response.ErrorResponse
import com.anafthdev.model.response.SuccessResponse
import com.anafthdev.services.SubTodoService
import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.subTodoRouting(subTodoService: SubTodoService) {
    routing {
        post("/subTodo") {
            val subTodo = Gson().fromJson(call.receiveText(), ExposedSubTodo::class.java)
            val exposedSubTodo = subTodoService.insert(subTodo)
            call.respondText(
                contentType = ContentType.Application.Json,
                text = Gson().toJson(SuccessResponse(HttpStatusCode.Created.value, "Sub todo created", exposedSubTodo))
            )
        }

        get("/subTodo") {
            val todoId = call.request.queryParameters["todoId"]?.toInt()
            checkId(todoId) {
                val subTodo = subTodoService.getByTodoId(todoId!!)
                if (subTodo.isNotEmpty()) {
                    call.respondText(
                        contentType = ContentType.Application.Json,
                        text = Gson().toJson(SuccessResponse(HttpStatusCode.OK.value, "Sub todo found", subTodo))
                    )
                } else call.respondText(
                    contentType = ContentType.Application.Json,
                    text = Gson().toJson(ErrorResponse(HttpStatusCode.NotFound.value, "Sub todo empty"))
                )
            }
        }

        get("/subTodo/{id}") {
            val id = call.parameters["id"]?.toInt()
            checkId(id) {
                val subTodo = subTodoService.get(id!!)
                if (subTodo != null) {
                    call.respondText(
                        contentType = ContentType.Application.Json,
                        text = Gson().toJson(SuccessResponse(HttpStatusCode.OK.value, "Sub todo found", subTodo))
                    )
                } else call.respondText(
                    contentType = ContentType.Application.Json,
                    text = Gson().toJson(ErrorResponse(HttpStatusCode.NotFound.value, "Sub todo not found"))
                )
            }
        }

        put("/subTodo/{id}") {
            val id = call.parameters["id"]?.toInt()
            checkId(id) {
                val subTodo = Gson().fromJson(call.receiveText(), ExposedSubTodo::class.java)
                val exposedSubTodo = subTodoService.update(id!!, subTodo)
                call.respondText(
                    contentType = ContentType.Application.Json,
                    text = Gson().toJson(SuccessResponse(HttpStatusCode.OK.value, "Sub todo updated", exposedSubTodo))
                )
            }
        }

        delete("/subTodo/{id}") {
            val id = call.parameters["id"]?.toInt()
            checkId(id) {
                val exposedSubTodo = subTodoService.delete(id!!)
                call.respondText(
                    contentType = ContentType.Application.Json,
                    text = Gson().toJson(SuccessResponse(HttpStatusCode.OK.value, "SubTodo deleted", exposedSubTodo))
                )
            }
        }
    }
}
