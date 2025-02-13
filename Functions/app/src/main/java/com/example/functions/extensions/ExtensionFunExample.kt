package com.example.functions.extensions

/*
// TODO: Напишите extension-функцию для класса List<Int>
// 1. Назовите её sumOfEvens.
// 2. Подсчитайте сумму всех чётных чисел в списке.

// TODO: Напишите функцию processList
// 1. Считайте строку чисел, введённую пользователем.
// 2. Преобразуйте строку в список целых чисел.
// 3. Вызовите extension-функцию sumOfEvens для списка.
// 4. Выведите результат вычисления.
 */

fun List<Int>.sumOfEvens(): Int {
    var sum = 0
    for (num in this) {
        if (num % 2 == 0)
            sum += num
    }
    return sum
}

fun processList() {
    val nums = readln().trim().split(' ').map { it -> it.toInt() }
    val result = nums.sumOfEvens()
    println("Сумма чётных чисел: $result")
}

fun main() {
    processList()
}