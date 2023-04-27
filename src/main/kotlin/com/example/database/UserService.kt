package com.example.database

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class UserService {

    fun addUser(user: UsersTable) = transaction {
        UsersTable.insert {
            it[login] = user.login
        }
    }

    fun allFetchUsers() = transaction {
        val users = UsersTable.selectAll().map {
            UserDto(
                login = it[UsersTable.login]
            )
        }
        println(users)
    }
}