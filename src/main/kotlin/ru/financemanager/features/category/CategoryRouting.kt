package ru.financemanager.features.category

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.financemanager.features.edittransaction.DetailController

fun Application.configureCategoryRouting() {

    routing {
        get("/category") {
            CategoryController(call).getCategories()
        }
    }
}
