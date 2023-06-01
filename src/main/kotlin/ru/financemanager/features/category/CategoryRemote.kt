package ru.financemanager.features.category

import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponseRemote(
    val name: String
)

@Serializable
data class CategoryResponseAllRemote(
    val categories: List<CategoryResponseRemote>
)

