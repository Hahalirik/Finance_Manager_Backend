package ru.financemanager.dabase.detail

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ru.financemanager.dabase.transaction.Transaction
import ru.financemanager.dabase.transaction.TransactionDTO

object Detail : Table() {
    private val name = Detail.varchar("name", 45)
    private val id_detail = Detail.integer("id_detail")
    private val fk_id_transaction = Detail.integer("fk_id_transaction")
    private val price = Detail.decimal("price", 10, 2)

    fun insertDetail(detailDTO: DetailDTO) {
        transaction {
            Detail.insert {
                it[name] = detailDTO.name
                it[id_detail] = detailDTO.id_detail
                it[fk_id_transaction] = detailDTO.fk_id_transaction
                it[price] = detailDTO.price.toBigDecimal()
            }
        }
    }

    fun fetchAll(): List<DetailDTO> {
        return try {
            transaction {
                Detail.selectAll().toList()
                    .map {
                        DetailDTO(
                            name = it[Detail.name],
                            id_detail = it[id_detail],
                            fk_id_transaction = it[fk_id_transaction],
                            price = it[price].toString()
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}