package com.anafthdev.plugins

import io.ktor.server.application.*

fun Application.configureSecurity() {
//    authentication {
//        jwt("auth-jwt") {
//            // Configure realm
//            realm = AuthManager.realm
//
//            // Token verifier
//            verifier(
//                JWT.require(Algorithm.HMAC256(AuthManager.secret))
//                    .withAudience(AuthManager.audience)
//                    .withIssuer(AuthManager.issuer)
//                    .build()
//            )
//
//            // Validate JWT payload
//            validate { jwtCredential ->
//                if (jwtCredential.payload.getClaim("username").asString() != "") {
//                    JWTPrincipal(jwtCredential.payload)
//                } else null
//            }
//
//            // If auth fail
//            challenge { _, _ ->
//                call.respondText(
//                    contentType = ContentType.Application.Json,
//                    text = Gson().toJson(ErrorResponse(HttpStatusCode.Unauthorized.value, "Token is not valid or has expired"))
//                )
//            }
//        }
//    }
//    routing {
//        post("/login") {
//            call.respondText("Token: ${AuthManager.generateToken(call.receiveText())}")
//        }
//        authenticate("auth-jwt") {
//            get("/protected") {
//                val principal = call.principal<JWTPrincipal>()
//                val username = principal!!.payload.getClaim("username").asString()
//                val expiresAt = principal.expiresAt?.time?.minus(System.currentTimeMillis())
//                call.respondText("Hello, $username! Token is expired at $expiresAt ms.")
//            }
//        }
//    }
}
