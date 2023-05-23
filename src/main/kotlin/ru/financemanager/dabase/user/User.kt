package ru.financemanager.dabase.user

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object User : Table(){
    private val login = User.varchar("login", 45)
    private val password_hesh = User.varchar("password_hesh", 45)
    private val salt_password = User.varchar("salt_password", 45)
    private val email = User.varchar("e-mail", 45)

    fun insertUser(userDTO: UserDTO) {
        transaction {
            User.insert {
                it[login] = userDTO.login
                it[password_hesh] = userDTO.password_hesh
                it[salt_password] = userDTO.salt_password
                it[email] = userDTO.email ?: ""
            }
        }
    }

    fun getUser(login: String): UserDTO? {
        return try {
            transaction {
                val userModel = User.select { User.login.eq(login) }.single()
                UserDTO(
                    login = userModel[User.login],
                    password_hesh = userModel[password_hesh],
                    salt_password = userModel[salt_password],
                    email = userModel[email]
                )
            }
        } catch (e: Exception) {
            null
        }
    }
}