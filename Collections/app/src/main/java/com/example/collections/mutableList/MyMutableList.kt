package com.example.collections.mutableList

interface MyMutableList<T> {

    val size: Int

    operator fun get(index: Int): T

    operator fun set(index: Int, element: T): T

    fun clear()


    fun add(element: T)

    fun add(index: Int, element: T)


    fun remove(element: T)

    fun removeAt(index: Int)


    fun contains(element: T): Boolean


    operator fun plus(element: T)

    operator fun minus(element: T)
}