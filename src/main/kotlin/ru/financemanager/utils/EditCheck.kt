package ru.financemanager.utils

import ru.financemanager.dabase.tokens.Token


object TokenCheck {

    fun isTokenValid(token: String): Boolean = Token.fetchTokens().firstOrNull { it.token == token } != null

    //fun isTokenAdmin(token: String): Boolean = token == "bf8487ae-7d47-11ec-90d6-0242ac120003"
}