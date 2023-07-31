package com.anafthdev.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.time.Instant

object AuthManager {

    /**
     * Generate token
     */
    fun generateToken(username: String): String {
        return JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("username", username)
            .withExpiresAt(Instant.ofEpochMilli(System.currentTimeMillis() + 60000))
            .sign(Algorithm.HMAC256(secret))
    }

    // Configure JWT settings
    const val issuer = "issuer"
    const val audience = "audience"
    const val secret = "leeseosecret"
    const val realm = "realm"

}