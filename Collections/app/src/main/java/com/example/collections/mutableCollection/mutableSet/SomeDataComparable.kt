package com.example.collections.mutableCollection.mutableSet


class SomeDataComparable(val value: Int) : Comparable<SomeDataComparable> {
    override fun compareTo(other: SomeDataComparable): Int {
        return when {
            value > other.value -> 1
            value < other.value -> -1
            else -> 0
        }
    }
}