package ru.financemanager

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database
import ru.financemanager.features.category.configureCategoryRouting
import ru.financemanager.features.edittransaction.configureDetailRouting
import ru.financemanager.features.login.configureLoginRouting
import ru.financemanager.features.register.configureRegisterRouting
import ru.financemanager.features.transactions.configureTransactionRouting
import ru.financemanager.plugins.*

fun main() {
    Database.connect(
        url = "jdbc:mysql://localhost:3306/finance_manager",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "User",
        password = "123456tesT!)")

    embeddedServer(CIO, port = 80, module = Application::module)
    .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureSerialization()
    configureRegisterRouting()
    configureLoginRouting()
    configureDetailRouting()
    configureTransactionRouting()
    configureCategoryRouting()
}
