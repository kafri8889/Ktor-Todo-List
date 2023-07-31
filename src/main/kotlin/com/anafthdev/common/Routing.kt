package com.anafthdev.common

import com.anafthdev.model.response.ErrorResponse
import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*

suspend inline fun PipelineContext<Unit, ApplicationCall>.checkId(id: Int?, block: () -> Unit) {
    if (id == null) call.respondText(Gson().toJson(ErrorResponse(HttpStatusCode.NotFound.value, "Bad request: \"id\" not found")))
    else block()
}
