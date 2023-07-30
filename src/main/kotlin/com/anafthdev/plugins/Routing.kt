package com.anafthdev.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            cause.printStackTrace()
            call.respondText(text = "500: $cause | ${cause.message}" , status = HttpStatusCode.InternalServerError)
        }
    }
    install(AutoHeadResponse)
    routing {
        get("/") {
            call.respondText("mmmamba")
        }
        get("/dorojo") {
            call.respondText("fakin gaddesss")
        }
        get("/gson") {
            call.respond(mapOf("hello" to "world"))
        }
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
    }
}
