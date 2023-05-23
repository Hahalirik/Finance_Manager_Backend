package ru.financemanager.features.transactions

import kotlinx.serialization.Serializable

@Serializable
data class RegisterReceiveRemote(
    val login: String,
    val email: String,
    val password_hesh: String,
    val salt_password: String
)

@Serializable
data class RegisterResponseRemote(
    val token: String
)
