package com.anafthdev.services

import com.anafthdev.common.dbQuery
import com.anafthdev.entity.CategoryEntity
import com.anafthdev.entity.UserEntity
import com.anafthdev.model.ExposedCategory
import com.anafthdev.model.db.CategoryTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class CategoryService(database: Database) {

    init {
        transaction(database) {
            SchemaUtils.create(CategoryTable)
        }
    }

    suspend fun insert(category: ExposedCategory): ExposedCategory {
        return dbQuery {
            CategoryEntity.new {
                name = category.name
                userId = UserEntity[category.userId]
            }.toExposedCategory()
        }
    }

    suspend fun get(id: Int): ExposedCategory? {
        return dbQuery { CategoryEntity.findById(id)?.toExposedCategory() }
    }

    suspend fun getByUserId(id: Int): List<ExposedCategory> {
        return dbQuery { CategoryEntity.find { CategoryTable.userId eq id }.map { it.toExposedCategory() } }
    }

    suspend fun update(id: Int, category: ExposedCategory) {
        dbQuery {
            CategoryTable.update({ CategoryTable.id eq id }) {
                it[name] = category.name
            }
        }
    }

    suspend fun delete(id: Int) {
        dbQuery {
            CategoryTable.deleteWhere { CategoryTable.id eq id }
        }
    }

}