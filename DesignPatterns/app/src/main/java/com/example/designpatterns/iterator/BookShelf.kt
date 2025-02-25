package com.example.designpatterns.iterator

// какой-то объект, по объектам которого надо всегда проходиться в цикле
// эти объекты могут быть любого типа, не только list

class BookShelf(private val books: List<Book>) : Iterable<Book> {
    override fun iterator(): Iterator<Book> {
        return BookShelfSortTitleIterator(books)
    }
}

class SomeBookShelf(private val techBooks: Book, private val childBook: Book) : Iterable<Book> {
    override fun iterator(): Iterator<Book> {
        return SomeBookShelfIterator(techBooks, childBook)
    }
}

fun main() {
    val shelf = SomeBookShelf(
        Book(1, "Kotlin"), Book(3, "Fairy Tail"),
    )

    shelf.forEach(::println)
}