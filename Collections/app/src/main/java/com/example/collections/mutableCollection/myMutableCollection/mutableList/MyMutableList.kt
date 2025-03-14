package com.example.collections.mutableCollection.myMutableCollection.mutableList

import com.example.collections.mutableCollection.myCollection.MyList
import com.example.collections.mutableCollection.myMutableCollection.MyMutableCollection
import com.example.collections.mutableCollection.myMutableCollection.mutableList.list.MyMutableListImpl

interface MyMutableList<T> : MyList<T>, MyMutableCollection<T> {
    // могли бы просто удалить потому что есть в интерфейсе родительском, но тогда не выкидывваться полный список доступных методов
    override val size: Int

    override operator fun get(index: Int): T

    operator fun set(index: Int, element: T): T

    override fun clear()


    override fun add(element: T): Boolean

    fun add(index: Int, element: T)


    override fun remove(element: T)

    fun removeAt(index: Int)


    override fun contains(element: T): Boolean


    operator fun plus(element: T)

    operator fun minus(element: T)
}

fun <T> myMutableListOf(vararg args: T): MyMutableList<T> {
    return MyMutableListImpl(*args)
}
