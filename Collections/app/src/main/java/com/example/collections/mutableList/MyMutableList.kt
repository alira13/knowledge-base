package com.example.collections.mutableList

interface MyMutableList {

    val size: Int

    fun add(element: Int)

    operator fun plus(element: Int)

    fun add(index: Int, element: Int)

    operator fun get(index: Int): Int

    fun removeAt(index: Int)

    fun remove(element: Int)

    operator fun minus(element: Int)

    fun clear()

    fun contains(element: Int):Boolean

    operator fun set(index: Int, element: Int): Int
}