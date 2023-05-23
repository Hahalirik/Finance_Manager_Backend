package ru.financemanager.features.edittransaction

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.financemanager.features.login.LoginController

fun Application.configureDetailRouting() {

    routing {
        post("/detail") {
            DetailController(call).getDetails()
        }
    }
}