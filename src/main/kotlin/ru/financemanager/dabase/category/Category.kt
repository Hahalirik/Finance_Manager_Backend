package ru.financemanager.dabase.category

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ru.financemanager.dabase.tokens.Token
import ru.financemanager.dabase.tokens.TokenDTO

object Category : Table() {
    private val name = Category.varchar("name", 45)

    fun getCategory(name: String): List<CategoryDTO?> {
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