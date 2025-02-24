package com.example.collections.mutableList.linkedList

import com.example.collections.mutableList.MyMutableList

class MyDoublyLinkedListImpl : MyMutableList {

    fun print() {
        for (i in 0 until size) {
            println("current = ${getNode(i).item} current.next = ${getNode(i).next?.item} size = $size")
        }
    }

    override var size: Int = 0
        private set

    private var first: DoublyNode? = null

    private var last: DoublyNode? = null

    override fun get(index: Int) = getNode(index).item

    private fun getNode(index: Int): DoublyNode {
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

    override fun set(index: Int, element: Int): Int {
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

    override fun add(element: Int) {
        val prevLast = last
        last = DoublyNode(element, prev = prevLast, next = null)

        if (prevLast == null) {
            first = last
        } else {
            prevLast.next = last
        }

        size++
    }

    override fun add(index: Int, element: Int) {
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

    private fun unlink(node: DoublyNode) {
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

    override fun remove(element: Int) {
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


    override fun contains(element: Int): Boolean {
        var currentNode = first
        repeat(size) {
            if (currentNode?.item == element) {
                return true
            } else
                currentNode = currentNode?.next
        }
        return false
    }

    override fun plus(element: Int) {
        add(element)
    }

    override fun minus(element: Int) {
        remove(element)
    }

    class DoublyNode(var item: Int, var next: DoublyNode? = null, var prev: DoublyNode? = null)

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
}