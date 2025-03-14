package com.example.collections.mutableCollection.myMutableCollection.mutableList.linkedList

import com.example.collections.mutableCollection.myMutableCollection.mutableList.MyMutableList

class MySinglyLinkedListImpl<T> : MyMutableList<T>, Iterable<T> {

    fun print() {
        for (i in 0 until size) {
            println("current = ${getNode(i).item} current.next = ${getNode(i).next?.item} size = $size")
        }
    }

    override var size: Int = 0
        private set

    private var first: Node<T>? = null

    private var last: Node<T>? = null

    override fun get(index: Int) = getNode(index).item

    private fun getNode(index: Int): Node<T> {
        checkIndex(index)
        if (index == 0) return first!!
        if (index == size - 1) return last!!
        if (index >= size) throw IndexOutOfBoundsException()

        var current = first
        repeat(index) {
            current = current?.next
        }
        return current!!
    }

    override fun set(index: Int, element: T): T {
        checkIndex(index)
        val currentNode = getNode(index)
        currentNode.item = element
        return element
    }

    override fun clear() {
        size = 0
        first = null
        last = null
    }

    override fun add(element: T): Boolean {
        if (size == 0) {
            val newElement = Node(element)
            first = newElement
            last = newElement
            size++
            return true
        }
        val newElement = Node(element)
        last?.next = newElement
        last = newElement
        size++
        return true
    }

    override fun add(index: Int, element: T) {

        checkIndexForAdding(index)
        if (index == size) {
            add(element)
            return
        }
        if (index == 0) {
            val newNode = Node(element, first)
            first = newNode
            size++
            return
        }

        val before = getNode(index - 1)
        val after = before.next

        val newNode = Node(element, after)
        before.next = newNode
        size++


    }


    override fun remove(element: T) {
        if (first?.item == element) {
            removeAt(0)
            return
        }
        var before = first
        repeat(size) {
            val currentNode = before?.next
            if (currentNode?.item == element) {
                val after = currentNode?.next
                before?.next = after
                if (after == null) {
                    last = before
                }
                size--
                return
            } else before = before?.next
        }
    }

    override fun removeAt(index: Int) {
        checkIndex(index)
        if (index == 0 && size == 1) {
            clear()
        }
        if (index == 0) {
            val after = first!!.next
            first = after
            size--
            return
        }

        val before = getNode(index - 1)
        val after = before.next!!.next

        before.next = after
        if (after == null)
            last = before
        size--
    }


    override fun contains(element: T): Boolean {
        var currentNode = first
        repeat(size) {
            if (currentNode?.item == element) {
                return true
            } else
                currentNode = currentNode?.next
        }
        return false
    }

    override fun plus(element: T) {
        add(element)
    }

    override fun minus(element: T) {
        remove(element)
    }

    class Node<T>(var item: T, var next: Node<T>? = null)

    private fun checkIndex(index: Int) {
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException("Index: $index Size:$size")
        }
    }

    private fun checkIndexForAdding(index: Int) {
        if (index < 0 || index > size) {
            throw IndexOutOfBoundsException("Index: $index Size:$size")
        }
    }

    override fun iterator(): MutableIterator<T> {

        var nextNode = first

        return object : MutableIterator<T> {
            override fun hasNext(): Boolean {
                return nextNode != null
            }

            override fun next(): T {
                val item = nextNode?.item
                nextNode = nextNode?.next
                return item!!
            }

            override fun remove() {
                TODO("Not yet implemented")
            }
        }
    }
}