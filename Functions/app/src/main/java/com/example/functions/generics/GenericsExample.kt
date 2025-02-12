package com.example.functions.generics


fun <T, R> transform(inCollection: List<T>, lambda: (T) -> R): List<R> {
    val resultCollection = mutableListOf<R>()
    for (item in inCollection) {
        val newItem = lambda(item)
        resultCollection.add(newItem)
    }
    return resultCollection
}

fun main(){
    val items = listOf(1, 2, 3)
    val newItems = transform(items) {
        it+it
    }
    println(newItems)
}