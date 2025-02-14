package com.example.functions.extensions

inline fun <T> Collection<T>.myForEach(lambda: (T) -> Unit) {
    for (item in this) {
        lambda(item)
    }
}

inline fun <T> Collection<T>.myFilter(lambda: (T) -> Boolean): Collection<T> {
    val result = mutableListOf<T>()
    for (item in this) {
        if (lambda(item))
            result.add(item)
    }
    return result
}

fun main() {
    println("foreach")
    val list = listOf(1, 2, 3, null)
    list.myForEach { println("$it") }

    println("\nfilter")
    list
        .myFilter { (it ?: 0) < 2 }
        .myForEach { println("$it") }


}