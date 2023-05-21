package ru.financemanager

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database
import ru.financemanager.plugins.*

fun main() {
    Database.connect(
        url = "jdbc:mysql://localhost:3306/finance_manager",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = "F;Tju_8Iuj:v")

    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
    .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureSerialization()
}
