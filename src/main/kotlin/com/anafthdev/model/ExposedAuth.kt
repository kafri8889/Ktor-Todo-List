package com.anafthdev.model

/**
 * Request body
 */
data class ExposedAuth(
    /**
     * access token or refresh token
     */
    val token: String,
    /**
     * if true, [token] is refresh token, otherwise [token] is access token
     */
    val isRefreshToken: Boolean
)
