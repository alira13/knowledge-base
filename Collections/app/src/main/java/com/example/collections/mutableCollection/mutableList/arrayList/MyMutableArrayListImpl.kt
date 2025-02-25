package com.example.collections.mutableCollection.mutableList.arrayList

import com.example.collections.mutableCollection.mutableList.MyMutableList

class MyMutableArrayListImpl<T> : MyMutableList<T>, Iterable<T> {

    // выделение памяти для массива и заполнения nulls
    private var elements = arrayOfNulls<Any>(INITIAL_CAPACITY)


    // текущий реализный размер массива(с непустыми элементами)
    override var size: Int = 0
        private set

    // добавление элемента в конец
    // O(1)
    // чтобы можно было использовать один интерфейс с hashSet,
    // добавляем возврашаемый тип. Мы всегда можем добавить в массив поэтому true, а у hashSet уже будет варьироваться
    override fun add(element: T): Boolean {
        growIfNeeded() // не идет в расчет, так как не очень часто проихсодит
        elements[size] = element
        size++
        return true
    }

    private fun growIfNeeded() {
        if (elements.size == size) {
            val newStaticArray = arrayOfNulls<Any>(size * 2)
            /*
            for (index in staticArray.indices) {
                newStaticArray[index] = staticArray[index]
            }
             */
            System.arraycopy(elements, 0, newStaticArray, 0, size)
            elements = newStaticArray
        }
    }

    // O(n)
    override fun add(index: Int, element: T) {
        checkIndexForAdding(index)
        growIfNeeded()
        // поскольку сам массив всегда больше,
        // чем количество элементов в нем,
        // то size<staticArray.size
        // как только size==staticArray.size, наш массив увеличивается вдвое

        /*
        for (i in size downTo index + 1) {
            staticArray[i] = staticArray[i - 1]
        }
         */

        System.arraycopy(elements, index, elements, index + 1, size - index)
        elements[index] = element
        size++
    }

    override fun plus(element: T) {
        add(element)
    }

    // получение элемента по интексу
    // O(1)
    override fun get(index: Int): T {
        checkIndex(index)

        return elements[index] as T
    }

    // удаление элемента по индексу
    // O(n)
    override fun removeAt(index: Int) {
        checkIndex(index)
        /*
        for (i in index until size - 1) {
            staticArray[i] = staticArray[i + 1]
        }
         */
        System.arraycopy(elements, index + 1, elements, index, size - index - 1)
        size--
        elements[size] = null
    }

    // удаление элемента по элементу
    // O(n)
    override fun remove(element: T) {
        for (index in elements.indices) {
            if (elements[index] == element) {
                removeAt(index)
                return
            }
        }
    }

    override fun minus(element: T) {
        remove(element)
    }

    // O(1)
    override fun clear() {
        elements = arrayOfNulls<Any>(INITIAL_CAPACITY)
        size = 0
    }

    // O(n)
    override fun contains(element: T): Boolean {
        for (index in 0 until size) {
            if (elements[index] == element) {
                return true
            }
        }
        return false
    }

    // O(1)
    override fun set(index: Int, element: T): T {
        checkIndex(index)
        elements[index] = element
        return element
    }

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

    companion object {
        // начальная емкость массива
        private const val INITIAL_CAPACITY = 10
    }

    override fun iterator(): Iterator<T> {
        return object : Iterator<T> {

            var nextIndex = 0

            override fun hasNext(): Boolean {
                return nextIndex < size
            }

            override fun next(): T {
                return elements[nextIndex++] as T
            }
        }
    }
}