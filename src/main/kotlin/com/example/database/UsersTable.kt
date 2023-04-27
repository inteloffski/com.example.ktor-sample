package com.example.database

import org.jetbrains.exposed.sql.Table


object UsersTable: Table("users") {
    val login = varchar(name = "login", length = 25)
}