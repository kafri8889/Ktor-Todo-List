package com.anafthdev.routing

import com.anafthdev.common.checkId
import com.anafthdev.model.ExposedCategory
import com.anafthdev.model.response.ErrorResponse
import com.anafthdev.model.response.SuccessResponse
import com.anafthdev.services.CategoryService
import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.categoryRouting(categoryService: CategoryService) {
    routing {
        post("/category") {
            val category = Gson().fromJson(call.receiveText(), ExposedCategory::class.java)
            val exposedCategory = categoryService.insert(category).toExposedCategory()
            call.respondText(Gson().toJson(SuccessResponse(HttpStatusCode.Created.value, "Category berhasil dibuat", exposedCategory)))
        }

        get("/category/{id}") {
            val id = call.parameters["id"]?.toInt()
            checkId(id) {
                val category = categoryService.get(id!!)
                if (category != null) {
                    call.respondText(Gson().toJson(category))
                } else call.respondText(Gson().toJson(ErrorResponse(HttpStatusCode.NotFound.value, "Category not found")))
            }
        }

        put("/category/{id}") {
            val id = call.parameters["id"]?.toInt()
            checkId(id) {
                val category = call.receive<ExposedCategory>()
                val exposedCategory = categoryService.update(id!!, category)
                call.respondText(Gson().toJson(SuccessResponse(HttpStatusCode.OK.value, "Category berhasil diupdate", exposedCategory)))
            }
        }

        delete("/category/{id}") {
            val id = call.parameters["id"]?.toInt()
            checkId(id) {
                val exposedCategory = categoryService.delete(id!!)
                call.respondText(Gson().toJson(SuccessResponse(HttpStatusCode.OK.value, "Category berhasil dihapus", exposedCategory)))
            }
        }
    }
}