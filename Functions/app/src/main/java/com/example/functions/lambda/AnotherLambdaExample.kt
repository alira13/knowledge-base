package com.example.functions.lambda

/*
Напишите функцию высшего порядка processNumbers, которая выполняет обработку списка чисел. Эта функция должна:

Принимать список чисел для обработки.
Использовать лямбда-выражение для отбора чисел, которые соответствуют определенному условию (возвращает Boolean).
Использовать лямбда-выражение для преобразования отобранных чисел (возвращает Int).
Возвращать новый список, содержащий преобразованные числа.
 */
fun processNumbers(
    inList: List<Int>,
    condition: (Int) -> Boolean,
    filter: (Int) -> Int
): List<Int> {
    val resultList = mutableListOf<Int>()
    for (item in inList) {
        if (condition(item)) {
            resultList.add(filter(item))
        }
    }
    return resultList
}

/*
Кроме того, создайте функцию startProcessing, которая:
Выводит сообщение "Введите числа, разделенные пробелами:".
Считывает список чисел, введенных пользователем одной строкой.
Передает этот список в функцию processNumbers с конкретными действиями:
Отобрать только числа больше 10.
Умножить отобранные числа на 3.
Выводит результат обработки с текстом: `"Результат обработки: [<результат>]".
 */

fun startProcessing() {
    println("Введите числа, разделенные пробелами:")
    val numbers = readln().trim().split(' ').map { it -> it.toInt() }
    val result = processNumbers(numbers, { it > 10 }, { it * 3 })
    println("Результат обработки: $result")
}

fun main() {
    startProcessing()
}

