package com.example.collections.mutableCollection.other

import com.example.collections.mutableCollection.myCollection.myListOf
import com.example.collections.mutableCollection.myMutableCollection.mutableList.MyMutableList

fun main() {
    val immutable = myListOf(1, 2, 3, 4, 5, 6, 7)
    (immutable as MyMutableList<Int>).add(100)
    immutable.forEach(::println)
}

fun method(list: List<Int>) {
    (list as MutableList<Int>).add(100)
}