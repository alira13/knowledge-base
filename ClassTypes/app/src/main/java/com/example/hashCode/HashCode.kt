package com.example.hashCode

// Примерный класс с правильной реализацией equals и hashCode с несколькими полями
class Customer(val customerId: Int, val name: String, val email: String, val address: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Customer) return false
        return customerId == other.customerId &&
                email == other.email &&
                address == other.address
    }

    override fun hashCode(): Int {
        var result = customerId
        result = 31 * result + email.hashCode()
        result = 31 * result + address.hashCode()
        return result
    }
}

// Класс User - переопределите методы equals и hashCode по userId
class User(val userId: Int, val name: String, val email: String) {
    // Сгенерированы через меню Generate
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        return userId == other.userId
    }

    override fun hashCode(): Int {
        return userId
    }

    override fun toString(): String {
        return "User(userId=$userId, name='$name', email='$email')"
    }

}

// Класс Account - переопределите методы equals и hashCode по accountId и email
class Account(val accountId: String, val userId: Int, val email: String) {
    // Сгенерированы через меню Generate

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Account

        if (accountId != other.accountId) return false
        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        var result = accountId.hashCode()
        result = 31 * result + email.hashCode()
        return result
    }

    override fun toString(): String {
        return "Account(accountId='$accountId', userId=$userId, email='$email')"
    }
}

fun main() {
    // если бы не переопределили методы, то в множествах были бы все элементы,
    // так как сравнение бы шло по ссылкам, а не полям

    val users = setOf(
        User(1, "Ivan", "Ivan@gmail.com"),
        User(2, "Ivan", "Ivan@gmail.com"),
        User(1, "Ivan", "Ivan@gmail.com"),
    )

    for (user in users)
        println(user)

    val accounts = setOf(
        Account("1", 1, "Ivan@gmail.com"),
        Account("1", 1, "Ivan1@gmail.com"),
        Account("1", 1, "Ivan@gmail.com")
    )

    for (acc in accounts)
        println(accounts)
}