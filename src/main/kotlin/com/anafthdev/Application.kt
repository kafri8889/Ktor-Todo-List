package com.anafthdev

import com.anafthdev.plugins.configureDatabases
import com.anafthdev.plugins.configureMonitoring
import com.anafthdev.plugins.configureRouting
import com.anafthdev.plugins.configureSecurity
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    System.setProperty("io.ktor.development", "true")

    embeddedServer(
        Netty,
        port = 80,
        host = "0.0.0.0",
        module = Application::module,
        watchPaths = listOf(
            "classes",
            "resources"
        )
    ).start(wait = true)
}

fun Application.module() {
    configureDatabases()
    configureMonitoring()
//    configureHTTP()
    configureSecurity()
    configureRouting()
}
