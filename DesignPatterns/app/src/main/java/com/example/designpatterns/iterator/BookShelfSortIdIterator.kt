package com.example.designpatterns.iterator

class BookShelfSortIdIterator(private val books: List<Book>) : Iterator<Book> {
    private var index = 0

    // Сортируем коллекцию по id
    private val sortedBook = books.sortedBy { it.id }

    override fun hasNext(): Boolean {
        return index < sortedBook.size
    }

    override fun next(): Book {
        if (!hasNext()) throw NoSuchElementException()
        val element = sortedBook[index]
        // Переходим через один элемент
        index ++
        return element
    }
}