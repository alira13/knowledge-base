package com.example.collections.mutableCollection.myMutableCollection.mutableSet


class MyComparable(val value: Int) : Comparable<MyComparable> {
    override fun compareTo(other: MyComparable): Int {
        return when {
            value > other.value -> 1
            value < other.value -> -1
            else -> 0
        }
    }
}