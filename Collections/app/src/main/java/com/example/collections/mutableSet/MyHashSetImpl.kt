package com.example.collections.mutableSet

import kotlin.math.abs

class MyHashSetImpl : MyMutableSet {

    // массив размером INITIAL_CAPACITY
    var elements = arrayOfNulls<Node>(INITIAL_CAPACITY)

    // количество элементо в массиве
    override var size: Int = 0
        private set

    // метод для расчета позиции
    private fun getElementPosition(element: Node, size: Int): Int {
        // значение массива может быть отрицательным, и тогда позиция будет отрицательна
        return (abs(element.item)) % size
    }

    override fun clear() {
        size = 0
        elements = arrayOfNulls<Node>(INITIAL_CAPACITY)
    }

    // Метод добавления элемента в массив
    override fun add(element: Int): Boolean {
        // условие расширения размера массива
        if (size >= elements.size * LOAD_FACTOR) {
            increaseSize()
        }

        // добавляем элемент в текущий массив
        // (первоначальный или тот, который сгенерил нам increaseSize)
        return add(element, elements).also { added ->
            // при успешном добавлени меняем количество элементов в массиве
            if (added) size++
        }
    }

    // вспомогательный метод для добавления элемента в произвольный массив
    private fun add(element: Int, array: Array<Node?>): Boolean {
        // создаем элемент и получаем его позицию на основе размера массива, в который мы хотим его добавить
        val newElement = Node(element)
        val position = getElementPosition(newElement, array.size)

        // читаем по этой позиции данные
        var existedElement = array[position]
        // если нет коллзии, то просто добавляем элемент
        if (existedElement == null) {
            array[position] = newElement
            return true
        }
        // есть коллизия и мы не знаем, сколько там элементов с связном списке
        else {
            while (true) {
                // если уже есть такой элемент с таким значением по такой позиции
                if (existedElement?.item == element)
                    return false
                // по этой позиции лежит другой элемент
                else {
                    // этот элемент в списке последний
                    if (existedElement?.next?.item == null) {
                        // тогда следующим за ним кладем наш элемент
                        existedElement?.next = newElement
                        return true
                    } else {
                        // элемент не последний, нужно для следующего также сделать все проверки
                        existedElement = existedElement.next
                    }
                }
            }
        }
    }

    // метод увеличения размера массива
    private fun increaseSize() {
        // создаем массив, в 2 раза больше существующего
        val newArray = arrayOfNulls<Node>(elements.size * 2)
        // нужно у всех существующих элементов пересчитать позицию(тк она зависит от size, который изменился)
        for (node in elements) {
            var currentElement = node
            // так как еще есть элементы в связном списке по позиции, их позиции тоже надо перегенерить и добавить в новый массив
            while (currentElement != null) {
                add(currentElement.item, newArray)
                // идем к следующему элементу
                currentElement = currentElement.next
            }
        }
        //новый массив создали, скинули в него все элементы старого с новыми позициями и присвоили текущему массиву
        elements = newArray
    }

    override fun remove(element: Int) {
        val position = getElementPosition(Node(element), elements.size)
        val existedElement = elements[position] ?: return

        if (existedElement.item == element) {
            elements[position] = existedElement.next
            size--
            return
        }

        var before: Node? = existedElement
        while (before?.next != null) {
            val removevingElement = before.next
            if (removevingElement?.item == element) {
                before.next = removevingElement.next
                size--
            } else {
                before = before.next
            }
        }

    }

    override fun contains(element: Int): Boolean {
        val position = getElementPosition(Node(element), elements.size)
        var existedItem = elements[position]

        while (existedItem != null) {
            if (existedItem.item == element)
                return true
            else
                existedItem = existedItem.next
        }

        return false
    }

    companion object {
        const val INITIAL_CAPACITY = 16
        const val LOAD_FACTOR = 0.75
    }

    data class Node(
        val item: Int,
        var next: Node? = null
    )
}

fun main() {
    val hashSet = MyHashSetImpl()
    repeat(100) {
        hashSet.add(it)
    }
    hashSet.elements.forEach(::println)
}