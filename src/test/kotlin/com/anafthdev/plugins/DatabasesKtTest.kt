package com.anafthdev.plugins

import com.anafthdev.module
import io.ktor.client.request.*
import io.ktor.server.testing.*
import kotlin.test.Test

class DatabasesKtTest {

    @Test
    fun testPostUsers() = testApplication {
        application {
            module()
        }
        client.post("/users").apply {

        }
    }
}