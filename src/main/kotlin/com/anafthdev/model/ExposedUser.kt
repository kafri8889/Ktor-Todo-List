package com.anafthdev.model

import com.anafthdev.common.Exposed
import com.anafthdev.model.db.User
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ResultRow

@Serializable
data class ExposedUser(
    val id: Int,
    val name: String,
    val email: String,
    val password: String
) {
    companion object: Exposed<ExposedUser> {
        override fun fromResultRow(resultRow: ResultRow): ExposedUser {
            return ExposedUser(
                resultRow[User.id],
                resultRow[User.name],
                resultRow[User.email],
                resultRow[User.password],
            )
        }
    }
}
