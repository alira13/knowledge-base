package com.example.exception

import Book

class Journal(val title: String) {

}

fun main() {
    val journal: Journal? = null
    val title = journal?.title?: throw Exception("Empty name")
    println(title)
}