package com.anafthdev.common

import org.jetbrains.exposed.sql.ResultRow

interface Exposed<T> {

    fun fromResultRow(resultRow: ResultRow): T

}