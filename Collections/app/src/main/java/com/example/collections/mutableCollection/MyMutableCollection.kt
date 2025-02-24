package com.example.collections.mutableCollection


interface MyMutableCollection<T> {
    val size: Int

    fun clear()

    fun add(element: T):Boolean

    fun remove(element: T)

    fun contains(element: T): Boolean
}