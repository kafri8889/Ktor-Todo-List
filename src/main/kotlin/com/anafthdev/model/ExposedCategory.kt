package com.anafthdev.model

import kotlinx.serialization.Serializable

@Serializable
data class ExposedCategory(
    val id: Int,
    val userId: Int,
    val name: String
)
