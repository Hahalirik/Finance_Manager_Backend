package ru.financemanager.dabase.category

import ru.financemanager.features.category.CategoryResponseRemote

class CategoryDTO(
    val name: String
)

fun CategoryDTO.mapToCategoryAllResponseRemote() : CategoryResponseRemote =
    CategoryResponseRemote(
        name = name
    )

