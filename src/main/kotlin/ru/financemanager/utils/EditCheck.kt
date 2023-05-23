package ru.financemanager.utils

import ru.financemanager.dabase.detail.Detail
import ru.financemanager.dabase.tokens.Token
import ru.financemanager.dabase.transaction.Transaction


object EditCheck {

    fun isTokenValid(token: String): Boolean = Token.fetchTokens().firstOrNull { it.token == token } != null
    fun isTransactionValid(id_transaction: String): Boolean = Transaction.fetchAll().firstOrNull { it.id_transaction == id_transaction } != null

    //fun isTokenAdmin(token: String): Boolean = token == "bf8487ae-7d47-11ec-90d6-0242ac120003"
}