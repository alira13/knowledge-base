package com.example.collections.mutableCollection.myMutableCollection.mutableList.list

import com.example.collections.mutableCollection.myMutableCollection.mutableList.MyMutableList

class MyMutableListImpl<T>(vararg elements: T) : MyMutableList<T> {

    private val array = elements

    override fun iterator(): MutableIterator<T> {
        return array.iterator() as MutableIterator
    }

    override val size: Int
        get() = array.size

    override fun get(index: Int): T {
        return array[index]
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun removeAt(index: Int) {
        TODO("Not yet implemented")
    }

    override fun minus(element: T) {
        TODO("Not yet implemented")
    }

    override fun plus(element: T) {
        TODO("Not yet implemented")
    }

    override fun remove(element: T) {
        TODO("Not yet implemented")
    }

    override fun add(index: Int, element: T) {
        TODO("Not yet implemented")
    }

    override fun add(element: T): Boolean {
        TODO("Not yet implemented")
    }

    override fun set(index: Int, element: T): T {
        TODO("Not yet implemented")
    }

    override fun contains(element: T): Boolean {
        return array.contains(element)
    }
}