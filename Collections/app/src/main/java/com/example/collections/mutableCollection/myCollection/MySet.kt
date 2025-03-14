package com.example.collections.mutableCollection.myCollection

interface MySet<T> : MyCollection<T> {

    override val size: Int

    override fun contains(element: T): Boolean
}
