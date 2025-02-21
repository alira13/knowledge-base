package com.example.collections.mutableList.arrayList

import com.example.collections.mutableList.MyMutableList

class MyMutableArrayListImpl : MyMutableList {

    // выделение памяти для массива и заполнения nulls
    private var staticArray = arrayOfNulls<Int>(INITIAL_CAPACITY)


    // текущий реализный размер массива(с непустыми элементами)
    override var size: Int = 0
        private set

    // добавление элемента в конец
    // O(1)
    override fun add(element: Int) {
        growIfNeeded() // не идет в расчет, так как не очень часто проихсодит
        staticArray[size] = element
        size++
    }

    private fun growIfNeeded() {
        if (staticArray.size == size) {
            val newStaticArray = arrayOfNulls<Int>(size * 2)
            /*
            for (index in staticArray.indices) {
                newStaticArray[index] = staticArray[index]
            }
             */
            System.arraycopy(staticArray, 0, newStaticArray, 0, size)
            staticArray = newStaticArray
        }
    }

    // O(n)
    override fun add(index: Int, element: Int) {
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

        System.arraycopy(staticArray, index, staticArray, index + 1, size - index)
        staticArray[index] = element
        size++
    }

    override fun plus(element: Int) {
        add(element)
    }

    // получение элемента по интексу
    // O(1)
    override fun get(index: Int): Int {
        checkIndex(index)
        return staticArray[index]!!
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
        System.arraycopy(staticArray, index + 1, staticArray, index, size - index - 1)
        size--
        staticArray[size] = null
    }

    // удаление элемента по элементу
    // O(n)
    override fun remove(element: Int) {
        for (index in staticArray.indices) {
            if (staticArray[index] == element) {
                removeAt(index)
                return
            }
        }
    }

    override fun minus(element: Int) {
        remove(element)
    }

    // O(1)
    override fun clear() {
        staticArray = arrayOfNulls<Int>(INITIAL_CAPACITY)
        size = 0
    }

    // O(n)
    override fun contains(element: Int): Boolean {
        for (index in 0 until size) {
            if (staticArray[index] == element) {
                return true
            }
        }
        return false
    }

    // O(1)
    override fun set(index: Int, element: Int): Int {
        checkIndex(index)
        staticArray[index] = element
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
}