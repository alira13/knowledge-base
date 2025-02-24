package com.example.collections.mutableSet

interface MyMutableSet {
    val size: Int

    fun clear()

    //Если успешно добавлено
    fun add(element: Int):Boolean

    fun remove(element: Int)

    fun contains(element: Int): Boolean
}