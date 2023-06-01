package ru.financemanager.features.category

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.financemanager.dabase.category.Category
import ru.financemanager.dabase.category.mapToCategoryAllResponseRemote
import ru.financemanager.dabase.detail.Detail
import ru.financemanager.dabase.detail.mapToDetailAllResponseRemote
import ru.financemanager.utils.EditCheck


class CategoryController(private val call: ApplicationCall) {

    suspend fun getCategories() {
        call.respond(CategoryResponseAllRemote(
            categories = Category.getCategories().map { it.mapToCategoryAllResponseRemote() }
        ))
    }
}

