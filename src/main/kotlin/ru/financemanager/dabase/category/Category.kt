package ru.financemanager.dabase.category

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Category : Table() {
    private val name = Category.varchar("name", 45)

    fun getCategory(name: String): CategoryDTO? {
        return try {
            transaction {
                val categoryModel = Category.select { Category.name.eq(name) }.single()
                CategoryDTO(
                    name = categoryModel[Category.name],
                )
            }
        } catch (e: Exception) {
            null
        }
    }
}