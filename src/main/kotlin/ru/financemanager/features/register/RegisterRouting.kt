package ru.financemanager.features.register

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.financemanager.features.register.RegisterController

fun Application.configureRegisterRouting() {

    routing {
        post("/register") {
            val registerController = RegisterController(call)
            registerController.registerNewUser()
        }
    }
}