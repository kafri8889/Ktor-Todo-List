package com.anafthdev.routing

import com.anafthdev.common.checkId
import com.anafthdev.model.ExposedTodo
import com.anafthdev.model.response.ErrorResponse
import com.anafthdev.model.response.SuccessResponse
import com.anafthdev.services.TodoService
import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.todoRouting(todoService: TodoService) {
    routing {
        post("/todo") {
            val todo = Gson().fromJson(call.receiveText(), ExposedTodo::class.java)
            val exposedTodo = todoService.insert(todo)
            call.respondText(
                contentType = ContentType.Application.Json,
                text = Gson().toJson(SuccessResponse(HttpStatusCode.Created.value, "Todo berhasil dibuat", exposedTodo))
            )
        }

        get("/todo/{id}") {
            val id = call.parameters["id"]?.toInt()
            checkId(id) {
                val todo = todoService.get(id!!)
                if (todo != null) {
                    call.respondText(
                        contentType = ContentType.Application.Json,
                        text = Gson().toJson(SuccessResponse(HttpStatusCode.OK.value, "Todo found", todo))
                    )
                } else call.respondText(
                    contentType = ContentType.Application.Json,
                    text = Gson().toJson(ErrorResponse(HttpStatusCode.NotFound.value, "Todo not found"))
                )
            }
        }

        get("/todo") {
            val categoryId = call.request.queryParameters["categoryId"]?.toInt()
            val userId = call.request.queryParameters["userId"]?.toInt()
            when {
                categoryId != null -> {
                    val todoList = todoService.getByCategoryId(categoryId)
                    call.respondText(
                        contentType = ContentType.Application.Json,
                        text = Gson().toJson(SuccessResponse(HttpStatusCode.OK.value, "Todo found", todoList))
                    )
                }
                userId != null -> {
                    val todoList = todoService.getByUserId(userId)
                    call.respondText(
                        contentType = ContentType.Application.Json,
                        text = Gson().toJson(SuccessResponse(HttpStatusCode.OK.value, "Todo found", todoList))
                    )
                }
                else -> checkId(null) {}
            }
        }

        put("/todo/{id}") {
            val id = call.parameters["id"]?.toInt()
            checkId(id) {
                val todo = Gson().fromJson(call.receiveText(), ExposedTodo::class.java)
                val exposedTodo = todoService.update(id!!, todo)
                call.respondText(
                    contentType = ContentType.Application.Json,
                    text = Gson().toJson(SuccessResponse(HttpStatusCode.OK.value, "Todo berhasil diupdate", exposedTodo))
                )
            }
        }

        delete("/todo/{id}") {
            val id = call.parameters["id"]?.toInt()
            checkId(id) {
                val exposedTodo = todoService.delete(id!!)
                call.respondText(
                    contentType = ContentType.Application.Json,
                    text = Gson().toJson(SuccessResponse(HttpStatusCode.OK.value, "Todo berhasil dihapus", exposedTodo))
                )
            }
        }
    }
}
