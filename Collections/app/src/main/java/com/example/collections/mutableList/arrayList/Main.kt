package com.example.collections.mutableList.arrayList

import kotlin.time.measureTime

fun main() {
    val numbers = mutableListOf<Int>()
    val time = measureTime {
        repeat(100_000) {
            numbers.add(it)
        }
    }
    println(time)
}