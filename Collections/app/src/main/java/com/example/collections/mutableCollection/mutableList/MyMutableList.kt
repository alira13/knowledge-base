package com.example.collections.mutableCollection.mutableList

import com.example.collections.mutableCollection.MyMutableCollection

interface MyMutableList<T> : MyMutableCollection<T>{
// могли бы просто удалить потому что есть в интерфейсе родительском, но тогда не выкидывваться полный список доступных методов
    override val size: Int

    operator fun get(index: Int): T

    operator fun set(index: Int, element: T): T

    override fun clear()


    override fun add(element: T):Boolean

    fun add(index: Int, element: T)


    override fun remove(element: T)

    fun removeAt(index: Int)


    override fun contains(element: T): Boolean


    operator fun plus(element: T)

    operator fun minus(element: T)
}