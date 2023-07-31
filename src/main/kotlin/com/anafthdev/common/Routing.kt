package com.anafthdev.common

import com.anafthdev.model.response.ErrorResponse
import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*

suspend inline fun PipelineContext<Unit, ApplicationCall>.checkId(id: Int?, block: () -> Unit) {
    if (id == null) call.respondText(
        contentType = ContentType.Application.Json,
        text = Gson().toJson(
            ErrorResponse(
                HttpStatusCode.BadRequest.value,
                "Bad request: Param or query not found"
            )
        )
    ) else block()
}
