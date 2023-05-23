package ru.financemanager.features.login

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.financemanager.features.login.LoginController

fun Application.configureLoginRouting() {

    routing {
        post("/login") {
            val loginController = LoginController(call)
            loginController.performLogin()
        }
    }
}