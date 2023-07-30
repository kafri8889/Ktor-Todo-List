package com.anafthdev.model.response

data class SuccessResponse(
    val code: Int,
    val message: String,
    val data: Any
)
