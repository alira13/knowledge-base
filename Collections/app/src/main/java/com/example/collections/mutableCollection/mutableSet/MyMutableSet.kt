package com.example.collections.mutableCollection.mutableSet

import com.example.collections.mutableCollection.MyMutableCollection

interface MyMutableSet<T>:MyMutableCollection<T> {
    override val size: Int

    override fun clear()

    //Если успешно добавлено
    override fun add(element: T): Boolean

    override fun remove(element: T)

    override fun contains(element: T): Boolean
}