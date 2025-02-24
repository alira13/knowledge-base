package com.example.collections.mutableSet

interface MyMutableSet<T> {
    val size: Int

    fun clear()

    //Если успешно добавлено
    fun add(element: T): Boolean

    fun remove(element: T)

    fun contains(element: T): Boolean
}