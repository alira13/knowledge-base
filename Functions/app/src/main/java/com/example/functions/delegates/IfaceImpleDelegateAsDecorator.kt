package com.example.functions.delegates

fun main() {
    val list: MutableList<Int> = mutableListOf(1, 2, 3)
    val loggedList = LoggedList(list)
    loggedList.add(1, 4)
    println(loggedList)
}

// Реализация декоратора
// благодаря by list нам не пришлось переопределять все методы MutableList,
// мы лишь переопределили нужны нам метод
data class LoggedList<T>(private val list: MutableList<T>) : MutableList<T> by list {
    override fun add(index: Int, element: T) {
        list.add(index, element).also {
            println("Added element list[$index] = $element")
        }
    }
}