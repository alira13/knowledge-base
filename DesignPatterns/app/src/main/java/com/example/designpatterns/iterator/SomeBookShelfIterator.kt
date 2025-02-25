package com.example.designpatterns.iterator

class SomeBookShelfIterator(private val techBooks: Book, private val childBook: Book) :
    Iterator<Book> {
    private var index = 0
    private val list = listOf(techBooks, childBook)

    override fun hasNext(): Boolean {
        return index < list.size
    }

    override fun next(): Book {
        if (!hasNext()) throw NoSuchElementException()

        return list[index++]
    }
}