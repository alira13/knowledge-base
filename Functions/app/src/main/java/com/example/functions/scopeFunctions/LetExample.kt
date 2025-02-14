package com.example.functions.scopeFunctions

/**
 * Класс для представления пользователя.
 */
data class User(val name: String, val email: String?)

/**
 * Отправляет сообщение на указанный email.
 * Реализация метода дана для примера.
 */
fun sendEmail(email: String) {
    println("Сообщение отправлено на $email")
}

/**
 * Проверяет email пользователя и вызывает sendEmail, если email не null.
 *
 * @param user объект пользователя с возможным отсутствующим email.
 */
fun processUserEmail(user: User) {
    // Реализуйте логику обработки email с использованием функции let
    user.email?.let {
        sendEmail(it)
    }
}