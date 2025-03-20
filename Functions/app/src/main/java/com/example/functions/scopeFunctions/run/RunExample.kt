package com.example.functions.scopeFunctions.run

private data class User(var name: String, var isActive: Boolean)

private fun main() {
    val user: User = User("John Doe", true) // Nullable объект

    // Реализация без использования scope-функций
    val result = if (user != null) {
        if (user.isActive) "User ${user.name} is active" else "User ${user.name} is not active"
    } else {
        null
    }

    // Реализация с использованием with
    val withResult = if (user != null) {
        with(user) {
            if (isActive) "User $name is active" else "User $name is not active"
        }
    } else {
        null
    }

    // Реализация с использованием let
    val letResult = user?.let {
        if (it.isActive) "User ${it.name} is active" else "User ${it.name} is not active"
    }

    // Реализация с использованием run
    val runResult = user?.run {
        if (isActive) "User $name is active" else "User $name is not active"
    }

    // Реализация с использованием apply
    // apply здесь не подходит, потому что:
    // 1. apply используется для настройки объекта (this) и возвращает его.
    // 2. Нам нужен результат вычисления в виде строки, а не сам объект User.
    val applyResult = null // Не применимо в данном случае.

    // Реализация с использованием also
    // also здесь не подходит, потому что:
    // 1. also возвращает исходный объект, а не результат вычислений.
    // 2. Нам нужно вернуть строку, а не объект User.
    val alsoResult = null // Не применимо в данном случае.

    // Вывод всех результатов
    println("result: $result")
    println("withResult: $withResult")
    println("letResult: $letResult")
    println("runResult: $runResult")
    println("applyResult: $applyResult")
    println("alsoResult: $alsoResult")
}