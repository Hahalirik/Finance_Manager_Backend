package ru.financemanager.features.transactions

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.financemanager.features.register.RegisterController

fun Application.configureTransactionRouting() {

    routing {
        get("/transactions/login") {
            val transactionController = TransactionController(call)
            transactionController.getTransactionsByLogin()
        }

        post("/transaction/category"){
            TransactionController(call).getTransactionsByCategory()
        }

        post("/transaction/datetime"){
            TransactionController(call).getTransactionsByDateTime()
        }

        post("/transaction/add"){
            TransactionController(call).addTransaction()
        }
    }
}

