package com.example.designpatterns.iterator

class BookShelfIterator(private val books: List<Book>) : Iterator<Book> {
    private var index = 0

    override fun hasNext(): Boolean {
        return index < books.size
    }

    override fun next(): Book {
        if (!hasNext()) throw NoSuchElementException()
        return books[index++]
    }
}