package ru.financemanager.dabase.category

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Category : Table() {
    private val name = Category.varchar("name", 45)

    fun getCategories(): List<CategoryDTO> {
        return try {
            transaction {
                Category.selectAll().toList()
                    .map {
                        CategoryDTO(
                            name = it[Category.name]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}