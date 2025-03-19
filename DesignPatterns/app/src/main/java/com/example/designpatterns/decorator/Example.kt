package com.example.designpatterns.decorator

fun main() {
    val list: MutableList<Int> = mutableListOf(1, 2, 3)
    val loggedList = LoggedList(list)
    loggedList.add(1, 4)
    println(loggedList)
}

data class LoggedList<T>(private val list: MutableList<T>) : MutableList<T> by list {
    override fun add(index: Int, element: T) {
        list.add(index, element).also {
            println("Added element list[$index] = $element")
        }
    }
}