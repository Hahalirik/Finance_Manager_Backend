package ru.financemanager.features.edittransaction

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.financemanager.dabase.detail.Detail
import ru.financemanager.dabase.tokens.TokenDTO
import ru.financemanager.dabase.tokens.Token
import ru.financemanager.dabase.transaction.Transaction
import ru.financemanager.dabase.user.User
import ru.financemanager.utils.EditCheck
import java.util.*


class DetailController(private val call: ApplicationCall) {

    suspend fun getDetails() {
        val receive = call.receive<DetailReceiveIdTransactionRemote>()
        if (!EditCheck.isTransactionValid(receive.id_transaction)) {
            call.respond(HttpStatusCode.Unauthorized, "Token expired")
        } else {
            call.respond(Detail.fetchAll().filter { it.fk_id_transaction.contains(receive.id_transaction, ignoreCase = true) })
        }
    }
}
