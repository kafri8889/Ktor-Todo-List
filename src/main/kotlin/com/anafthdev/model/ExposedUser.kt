package com.anafthdev.model

import kotlinx.serialization.Serializable

@Serializable
data class ExposedUser(
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val categories: List<ExposedCategory>? = null,
    val todo: List<ExposedTodo>? = null
)
