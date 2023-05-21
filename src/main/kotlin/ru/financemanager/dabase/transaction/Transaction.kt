package ru.financemanager.dabase.transaction

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Transaction : Table() {
    private val name = Transaction.varchar("name", 45)
    private val user_login = Transaction.varchar("user_login", 45)
    private val id_transaction = Transaction.integer("id_transaction")
    private val date_time = Transaction.varchar("date_time", 12)
    private val summ_price = Transaction.decimal("summ_price", 10, 2)
    private val category_name = Transaction.varchar("category_name", 45)

    fun insertTransaction(transactionDTO: TransactionDTO) {
        transaction {
            Transaction.insert {
                it[name] = transactionDTO.name
                it[user_login] = transactionDTO.user_login
                it[id_transaction] = transactionDTO.id_transaction
                it[date_time] = transactionDTO.date_time
                it[summ_price] = transactionDTO.summ_price.toBigDecimal()
                it[category_name] = transactionDTO.category_name
            }
        }
    }

    fun fetchAll(): List<TransactionDTO> {
        return try {
            transaction {
                Transaction.selectAll().toList()
                    .map {
                        TransactionDTO(
                            name = it[name],
                            user_login = it[user_login],
                            id_transaction = it[id_transaction],
                            date_time = it[date_time],
                            summ_price = it[summ_price].toString(),
                            category_name = it[category_name]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}