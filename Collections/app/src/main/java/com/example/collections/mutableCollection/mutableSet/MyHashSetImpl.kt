package com.example.collections.mutableCollection.mutableSet

import kotlin.math.abs

class MyHashSetImpl<T> : MyMutableSet<T>, Iterable<T> {

    // массив размером INITIAL_CAPACITY
    var elements = arrayOfNulls<Node<T>>(INITIAL_CAPACITY)

    // количество элементо в массиве
    override var size: Int = 0
        private set

    // метод для расчета позиции
    private fun <T> getElementPosition(element: Node<T>, size: Int): Int {
        // значение массива может быть отрицательным, и тогда позиция будет отрицательна
        return abs(element.hashCode() % size)
    }

    override fun clear() {
        size = 0
        elements = arrayOfNulls(INITIAL_CAPACITY)
    }

    // Метод добавления элемента в массив
    override fun add(element: T): Boolean {
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
    private fun <T> add(element: T, array: Array<Node<T>?>): Boolean {
        // создаем элемент и получаем его позицию на основе размера массива, в который мы хотим его добавить
        val newElement = Node<T>(element)
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
        val newArray = arrayOfNulls<Node<T>>(elements.size * 2)
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

    override fun remove(element: T) {
        val position = getElementPosition(Node(element), elements.size)
        val existedElement = elements[position] ?: return

        if (existedElement.item == element) {
            elements[position] = existedElement.next
            size--
            return
        }

        var before: Node<T>? = existedElement
        while (before?.next != null) {
            val removingElement = before.next
            if (removingElement?.item == element) {
                before.next = removingElement?.next
                size--
            } else {
                before = before.next
            }
        }

    }

    override fun contains(element: T): Boolean {
        val position = getElementPosition(Node<T>(element), elements.size)
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

    data class Node<T>(
        val item: T,
        var next: Node<T>? = null
    )

    override fun iterator(): Iterator<T> {
        // индекс массива ячейки, с которой мы работаем
        var nodeIndex = 0
        // следующий элемент
        var nextNode = elements[nodeIndex]
        // количество элементов, которые уже перебрали
        var nextIndex = 0

        return object : Iterator<T> {
            // если количество элементов, которые уже перебрали, меньше всех не null объектов массива
            override fun hasNext(): Boolean {
                return nextIndex < size
            }

            // ищем не null-ячейку последовательно идя по ячейкам,
            // как только нашли  ячейку, выводим ее значение
            // переходим по цепочке next пока next!=null
            override fun next(): T {
                while (nextNode == null) {
                    nextNode = elements[++nodeIndex]
                }
                return nextNode?.item!!.also {
                    nextIndex++
                    nextNode = nextNode?.next
                }
            }
        }
    }
}

fun main() {
    val hashSet = MyHashSetImpl<Int>()
    repeat(100) {
        hashSet.add(it)
    }
    hashSet.elements.forEach(::println)
}