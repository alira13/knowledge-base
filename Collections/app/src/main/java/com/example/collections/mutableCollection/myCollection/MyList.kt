package com.example.collections.mutableCollection.myCollection

interface MyList<out T> : MyCollection<T> {

    override val size: Int

    operator fun get(index: Int): T

    override fun contains(element: @UnsafeVariance T): Boolean
}

fun <T> myListOf(vararg elements: T): MyList<T> {
    return MyListImpl(*elements)
}
