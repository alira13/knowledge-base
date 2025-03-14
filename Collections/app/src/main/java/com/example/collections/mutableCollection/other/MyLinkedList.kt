package com.example.collections.mutableCollection.other

import com.example.collections.mutableCollection.myMutableCollection.mutableList.MyMutableList

class MyLinkedList<T> : MyMutableList<T> {

    private var modCount = 0
    private var first: Node<T>? = null
    private var last: Node<T>? = null

    override var size: Int = 0
        private set

    override fun add(element: T): Boolean {
        modCount++
        val prevLast = last
        last = Node(prevLast, element)
        if (prevLast == null) {
            first = last
        } else {
            prevLast.next = last
        }
        size++
        return true
    }

    override fun add(index: Int, element: T) {
        modCount++
        checkIndexForAdding(index)
        if (index == size) {
            add(element)
            return
        }
        if (index == 0) {
            val node = Node(null, element, first)
            first?.prev = node
            first = node
            size++
            return
        }
        val before = getNode(index - 1)
        val after = before.next
        val newNode = Node(before, element, after)
        before.next = newNode
        after?.prev = newNode
        size++
    }

    override fun plus(element: T) {
        add(element)
    }

    override fun minus(element: T) {
        remove(element)
    }

    override fun get(index: Int): T {
        checkIndex(index)
        return getNode(index).item
    }

    override fun set(index: Int, element: T): T {
        TODO("Not yet implemented")
    }

    private fun getNode(index: Int): Node<T> {
        if (index == 0) return first!!
        if (index == size - 1) return last!!

        if (index < size / 2) {
            var node = first
            repeat(index) {
                node = node?.next
            }
            return node!!
        } else {
            var node = last
            repeat(size - index - 1) {
                node = node?.prev
            }
            return node!!
        }
    }

    override fun removeAt(index: Int) {
        modCount++
        checkIndex(index)
        val node = getNode(index)
        unlink(node)
    }

    private fun unlink(node: Node<T>) {
        modCount++
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
        modCount++
        var node = first
        repeat(size) {
            if (node?.item == element) {
                node?.let { unlink(it) }
                return
            } else {
                node = node?.next
            }
        }
    }

    override fun clear() {
        modCount++
        first = null
        last = null
        size = 0
    }

    override fun contains(element: T): Boolean {
        var node = first
        repeat(size) {
            if (node?.item == element) {
                return true
            } else {
                node = node?.next
            }
        }
        return false
    }

    private fun checkIndex(index: Int) {
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException("Index: $index Size: $size")
        }
    }

    private fun checkIndexForAdding(index: Int) {
        if (index < 0 || index > size) {
            throw IndexOutOfBoundsException("Index: $index Size: $size")
        }
    }

    override fun iterator(): MutableIterator<T> {
        return object : MutableIterator<T> {

            private val currentModCount = modCount
            private var nextNode = first

            override fun hasNext(): Boolean {
                return nextNode != null
            }

            override fun next(): T {
                if (currentModCount != modCount) throw ConcurrentModificationException()
                return nextNode?.item!!.also {
                    nextNode = nextNode?.next
                }
            }

            override fun remove() {
                TODO("Not yet implemented")
            }
        }
    }

    class Node<T>(
        var prev: Node<T>? = null,
        val item: T,
        var next: Node<T>? = null
    )
}