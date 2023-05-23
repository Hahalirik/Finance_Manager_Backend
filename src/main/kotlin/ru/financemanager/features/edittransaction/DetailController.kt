package ru.financemanager.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.financemanager.dabase.tokens.TokenDTO
import ru.financemanager.dabase.tokens.Token
import ru.financemanager.dabase.user.User
import java.util.*

class LoginController(private val call: ApplicationCall) {

    suspend fun performLogin() {
        val receive = call.receive<LoginReceiveRemote>()
        val userDTO = User.getUser(receive.login)

        if (userDTO == null) {
            call.respond(HttpStatusCode.BadRequest, "User not found")
        } else {
            if (userDTO.password_hesh == receive.password_hesh) {
                val token = UUID.randomUUID().toString()
                Token.insert(
                    TokenDTO(
                        id = UUID.randomUUID().toString(),
                        login = receive.login,
                        token = token
                    )
                )

                call.respond(LoginResponseRemote(token = token))
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid password")
            }
        }
    }
}