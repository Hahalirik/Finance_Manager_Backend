package ru.financemanager.features.transactions

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.financemanager.dabase.detail.Detail
import ru.financemanager.dabase.detail.DetailDTO

import ru.financemanager.dabase.tokens.Token
import ru.financemanager.dabase.transaction.Transaction
import ru.financemanager.dabase.transaction.TransactionDTO
import ru.financemanager.utils.EditCheck
import java.util.*

class TransactionController(private val call: ApplicationCall) {

    suspend fun getTransactionsByLogin() {
        val token = call.request.headers["Bearer-Authorization"]

        if (!EditCheck.isTokenValid(token.orEmpty())) {
            call.respond(HttpStatusCode.Unauthorized, "Token expired")
        } else {
            val tokenDTO = Token.getToken(token!!)
            if (tokenDTO != null) {
                call.respond(Transaction.getTransactions(tokenDTO.login))
            } else {
                call.respond(HttpStatusCode.BadRequest, "Token does not exist")
            }
        }
    }

    suspend fun getTransactionsByCategory() {
        val receive = call.receive<TransactionReceiveCategoryRemote>()

        if (!EditCheck.isTokenValid(receive.token)) {
            call.respond(HttpStatusCode.Unauthorized, "Token expired")
        } else {
            val tokenDTO = Token.getToken(receive.token)
            if (tokenDTO != null) {
                call.respond(Transaction.fetchAll().filter { it.category_name.contains(receive.category_name, ignoreCase = true) }.filter { it.user_login.contains(tokenDTO.login, ignoreCase = true) })
            } else {
                call.respond(HttpStatusCode.BadRequest, "Token does not exist")
            }
        }
    }

    suspend fun getTransactionsByDateTime() {
        val receive = call.receive<TransactionReceiveDateTimeRemote>()

        if (!EditCheck.isTokenValid(receive.token)) {
            call.respond(HttpStatusCode.Unauthorized, "Token expired")
        } else {
            val tokenDTO = Token.getToken(receive.token)
            if (tokenDTO != null) {
                call.respond(Transaction.getTransactionsDateTime(receive.start, receive.end).filter {  it.user_login.contains(tokenDTO.login, ignoreCase = true)  })
            } else {
                call.respond(HttpStatusCode.BadRequest, "Token does not exist")
            }
        }
    }

    suspend fun addTransaction() {
        val receive = call.receive<TransactionReceiveRemote>()
        val idTransaction = UUID.randomUUID().toString()

        if (!EditCheck.isTokenValid(receive.token)) {
            call.respond(HttpStatusCode.Unauthorized, "Token expired")
        } else {
            val tokenDTO = Token.getToken(receive.token)
            if (tokenDTO != null) {
                try {
                    Transaction.insertTransaction(
                        TransactionDTO(
                            name = receive.name,
                            user_login = tokenDTO.login,
                            id_transaction = idTransaction,
                            date_time = receive.date_time,
                            summ_price = receive.detailList.sumOf { i ->
                                i.price.toBigDecimal()
                            }.toString(),
                            category_name = receive.category_name
                        )
                    )
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, "Can't create transaction")
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, "Token does not exist")
            }
            receive.detailList.forEach(){ detail ->
                Detail.insertDetail(
                    DetailDTO(
                        name = detail.name,
                        id_detail = UUID.randomUUID().toString(),
                        fk_id_transaction = idTransaction,
                        price = detail.price
                    )
                )
            }

            call.respond(TransactionResponseRemote(id_transaction = idTransaction))
        }
    }
}