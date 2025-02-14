package com.example.functions.scopeFunctions

/**
 * Обрабатывает коллекцию строк с использованием цепочки преобразований.
 * @param strings Коллекция строк для обработки.
 * @return Преобразованная коллекция строк.
 */
fun processStrings(strings: List<String>): List<String> {
    return strings
        .also { println("Исходный список: $strings") }
        .filter { it.isNotBlank() }               // Убираем пустые строки
        .also { println("Этап 1: Осталось ${it.count()} непустых строк") }
        .map { it.trim() }                        // Убираем лишние пробелы
        .filter { it.length > 3 }                 // Оставляем строки длиной больше 3
        .also { println("Этап 2: Строки длиной более 3 символов: $it") }
        .sortedBy { it.length }                   // Сортируем по длине
        .also { println("Этап 3: Первые 3 строки после сортировки: ${it.take(3)}") }
        .map { it.uppercase() }                   // Преобразуем в верхний регистр
        .also { println("Этап 4: Все строки в верхнем регистре: $it") }
        .distinct()                               // Убираем дубликаты
        .take(5)                                  // Берём первые 5 строк
        .also { println("Этап 5: Итоговый результат: $it") }
}

fun main() {
    val str = listOf("Hello", "my", "friend", "How", "are", "you")
    processStrings(str)
}