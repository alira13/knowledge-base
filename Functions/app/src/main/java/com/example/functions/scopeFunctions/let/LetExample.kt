package com.example.functions.scopeFunctions.let

/**
 * Класс для представления пользователя.
 */
private data class User(val name: String, val email: String?)

/**
 * Отправляет сообщение на указанный email.
 * Реализация метода дана для примера.
 */
private fun sendEmail(email: String) {
    println("Сообщение отправлено на $email")
}

/**
 * Проверяет email пользователя и вызывает sendEmail, если email не null.
 *
 * @param user объект пользователя с возможным отсутствующим email.
 */
private fun processUserEmail(user: User) {
    // Реализуйте логику обработки email с использованием функции let
    user.email?.let {
        sendEmail(it)
    }
}