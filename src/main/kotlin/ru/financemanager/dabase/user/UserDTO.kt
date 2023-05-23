package ru.financemanager.dabase.user

class UserDTO(
    val login: String,
    val password_hesh: String,
    val salt_password: String,
    val email: String?
)