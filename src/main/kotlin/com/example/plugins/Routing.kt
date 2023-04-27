package com.example.plugins

import com.example.database.UserService
import com.example.database.UsersTable
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.selectAll

@Serializable
data class Nft(
    val id: Int,
    val nftName: String,
    val nftPrice: Double
)

fun Application.configureRouting() {

    val userService = UserService()

    routing {
        post(path = "/users") {
            try {
                val user = call.receive<UsersTable>()
                val login = userService.addUser(user)
                call.respond(mapOf("login" to login))
            } catch (e: Exception){
                call.respond(HttpStatusCode.BadRequest, mapOf("error" to e.message))
            }
        }
    }

    routing {
        get("/") {
            try {
                val users = userService.allFetchUsers()
                call.respond(mapOf("/" to users))
            } catch (e: Exception){
                call.respond(HttpStatusCode.BadRequest, mapOf("error" to e.message))
            }

        }
    }
}
