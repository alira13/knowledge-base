package com.example.designpatterns.iterator

class BookShelfReversedIterator(private val books: List<Book>) : Iterator<Book>{
    private var index = books.size - 1

    //следующе
    override fun hasNext(): Boolean {
        return index >=0
    }

    override fun next(): Book {
        if (!hasNext()) throw NoSuchElementException()
        return books[index--]
    }
}