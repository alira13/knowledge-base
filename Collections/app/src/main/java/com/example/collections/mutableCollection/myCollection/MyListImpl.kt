package com.example.collections.mutableCollection.myCollection

class MyListImpl<T>(vararg elements: T) : MyList<T> {

    private val array = elements

    override fun iterator(): Iterator<T> {
        return array.iterator()
    }

    override val size: Int
        get() = array.size

    override fun get(index: Int): T {
        return array[index]
    }

    override fun contains(element: T): Boolean {
        return array.contains(element)
    }
}