package com.example.backingField

import Book
import Library

fun main() {
    val library = Library("Библиотека")
    library.addBook(Book("Новая книга", "Достоевский"))
    println("Books:")
    library.printAllBooks()
    println("Users:")
    library.printAllUsers()
}