package com.example.collections.mutableList

interface MyMutableList {

    val size: Int

    operator fun get(index: Int): Int

    operator fun set(index: Int, element: Int): Int

    fun clear()


    fun add(element: Int)

    fun add(index: Int, element: Int)


    fun remove(element: Int)

    fun removeAt(index: Int)


    fun contains(element: Int): Boolean


    operator fun plus(element: Int)

    operator fun minus(element: Int)
}