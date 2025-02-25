package com.example.collections.mutableCollection.mutableList.linkedList

import com.example.collections.mutableCollection.mutableList.MyMutableList

class MyDoublyLinkedListImpl<T> : MyMutableList<T>, Iterable<T> {

    fun print() {
        for (i in 0 until size) {
            println("current = ${getNode(i).item} current.next = ${getNode(i).next?.item} size = $size")
        }
    }

    override var size: Int = 0
        private set

    private var first: DoublyNode<T>? = null

    private var last: DoublyNode<T>? = null

    override fun get(index: Int) = getNode(index).item

    private fun getNode(index: Int): DoublyNode<T> {
        checkIndex(index)
        if (index == 0) return first!!
        if (index == size - 1) return last!!

        if (index < size / 2) {
            var current = first
            repeat(index) {
                current = current?.next
            }
            return current!!
        } else {
            var current = last
            repeat(size - index - 1) {
                current = current?.prev
            }
            return current!!
        }
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
        val prevLast = last
        last = DoublyNode(element, prev = prevLast, next = null)

        if (prevLast == null) {
            first = last
        } else {
            prevLast.next = last
        }

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
            val newNode = DoublyNode(element, next = first, prev = null)
            first?.prev = newNode
            first = newNode
            size++
            return
        }

        val before = getNode(index - 1)
        val after = before.next

        val newNode = DoublyNode(element, next = after, prev = before)
        before.next = newNode
        after?.prev = newNode
        size++
    }

    private fun unlink(node: DoublyNode<T>) {
        val before = node.prev
        val after = node.next
        before?.next = after
        after?.prev = before
        if (after == null) {
            last = before
        }
        if (before == null) {
            first = after
        }
        size--
    }

    override fun remove(element: T) {
        var currentNode = first

        repeat(size) {
            if (currentNode?.item == element) {
                currentNode?.let {
                    unlink(it)
                }
                return
            } else
                currentNode = currentNode?.next
        }
    }

    override fun removeAt(index: Int) {
        checkIndex(index)
        val deletedNode = getNode(index)
        unlink(deletedNode)
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

    class DoublyNode<T>(
        var item: T,
        var next: DoublyNode<T>? = null,
        var prev: DoublyNode<T>? = null
    )

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

    override fun iterator(): ListIterator<T> {
        var currentNode = first
        var lastReturned: DoublyNode<T>? = null
        var index = 0

        return object : ListIterator<T> {
            override fun hasNext(): Boolean {
                return currentNode != null
            }

            override fun hasPrevious(): Boolean {
                return lastReturned != null
            }

            override fun next(): T {
                val value = currentNode!!.item

                lastReturned = currentNode
                currentNode = currentNode!!.next

                index++
                return value
            }

            override fun nextIndex(): Int {
                return index
            }

            override fun previous(): T {
                currentNode = lastReturned
                val value = currentNode!!.item

                lastReturned = currentNode!!.prev

                index--
                return value
            }

            override fun previousIndex(): Int {
                return (index - 1)
            }
        }
    }
}


fun main() {
    val list = MyDoublyLinkedListImpl<String>()
    list.add("A")
    list.add("B")
    list.add("C")
    list.add("D")

    val iterator = list.iterator()

    println("Forward:")
    while (iterator.hasNext()) {
        println(iterator.next()) // A, B, C, D
    }

    println("Back:")
    while (iterator.hasPrevious()) {
        println(iterator.previous()) // D, C, B, A
    }

    println("For:")
    list.forEach(::println)
}