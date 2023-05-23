package ru.financemanager.dabase.transaction

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import ru.financemanager.dabase.tokens.Token
import ru.financemanager.dabase.tokens.TokenDTO
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Transaction : Table() {
    private val name = Transaction.varchar("name", 45)
    private val user_login = Transaction.varchar("user_login", 45)
    private val id_transaction = Transaction.varchar("id_transaction", 50)
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

    fun deleteTransaction(transactionDTO: TransactionDTO){
        transaction {
            Transaction.deleteWhere { id_transaction eq transactionDTO.id_transaction  }
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

    fun getTransactions(us_login: String): List<TransactionDTO> {
        return try {
            transaction {
                Transaction.select { Transaction.user_login.eq(us_login) }.toList()
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

    fun getTransactionsDateTime(start: String, end: String): List<TransactionDTO> {
        return try {
            transaction {
                Transaction.select { Transaction.date_time.between(start, end)}.toList()
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