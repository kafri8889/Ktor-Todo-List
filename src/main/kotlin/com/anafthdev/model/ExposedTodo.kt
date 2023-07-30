package com.anafthdev.model

import kotlinx.serialization.Serializable

@Serializable
data class ExposedTodo(
    val userId: Int,
    val categoryId: Int,
    val title: String,
    val description: String,
    val createdAt: Long,
    val finished: Boolean,
    val subTodo: List<ExposedSubTodo>? = null
)
