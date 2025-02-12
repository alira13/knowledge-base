package com.example.serialization

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.File

// Класс User, который необходимо сериализовать и десериализовать
@Serializable
data class User(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("email") val email: String,
    @SerialName("registered_at") val registeredAt: String
)

// Реализуйте эту функцию для сериализации объекта User
fun serializeUser(user: User): String {
    return Json.encodeToString(user)
}

// Реализуйте эту функцию для десериализации строки JSON в объект User
fun deserializeUser(json: String): User {
    return Json.decodeFromString<User>(json)
}

fun main() {

    val users = listOf(
        User(1, "Ivan", "Ivan@mail.ru", "11.01.2021"),
        User(2, "Maria", "Maria@mail.ru", "11.01.2022")
    )

    // пример для 1 объекта
    val userFile = File("user.json")
    val strToFile = serializeUser(users[0])
    userFile.writeText(strToFile)

    val strFromFile = userFile.readText()
    val user = deserializeUser(strFromFile)

    println(user)

    // пример для массива
    val usersFile = File("users.json")
    val usersString = Json.encodeToString(users)
    usersFile.appendText(usersString)
    val usersFromFile = usersFile.readText()
    val usersFromJson = Json.decodeFromString<List<User>>(usersFromFile)

    println(usersFromJson)
}
