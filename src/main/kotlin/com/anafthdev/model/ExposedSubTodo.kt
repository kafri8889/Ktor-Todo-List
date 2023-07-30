package com.anafthdev.model

import kotlinx.serialization.Serializable

@Serializable
data class ExposedSubTodo(
    val todoId: Int,
    val title: String,
    val createdAt: Long,
    val finished: Boolean,
)
