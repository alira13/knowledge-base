package com.example.collections.mutableCollection.myMutableCollection

import com.example.collections.mutableCollection.myCollection.MyCollection


interface MyMutableCollection<T> : MyCollection<T>, Iterable<T> {
    override val size: Int

    fun clear()

    fun add(element: T): Boolean

    fun remove(element: T)

    override fun contains(element: T): Boolean
}


