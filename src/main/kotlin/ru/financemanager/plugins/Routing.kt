package ru.financemanager.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import kotlinx.serialization.Serializable

@Serializable
data class Test(
    val text: String
)

fun Application.configureRouting() {
    routing {
        get("/login") {
            call.respond(Test(text = "hello?"))
        }
    }
}
