package com.example.collections.mutableCollection.myMutableCollection.mutableSet

fun main() {
    //printHashSet()

    // при добавлении добавляются ссылки не предыдущий и следующий элементы,
    // что позволяет выстраивать коллецию в порядке добавления элементов
    // занимает больше памяти за счет доп ссылок
    // остальные сложности такие же
    //printLinkedHashSet()

    // все объекты хранятся в отсортированном виде, в данном случае в алфавитном порядке для строк
    // обязательна реализация интерфейса Comparable чтобы treeSet знал как сортировать
    printTreeSet()
}

fun printLinkedHashSet() {
    println("printLinkedHashSet")
    val numbers = mutableSetOf<String>()
    repeat(100) {
        numbers.add("Number: $it")
    }

    numbers.forEach(::println)
}

fun printHashSet() {
    println("printHashSet")
    val numbers = hashSetOf<String>()
    repeat(100) {
        numbers.add("Number: $it")
    }

    numbers.forEach(::println)
}

fun printTreeSet() {
    println("printHashSet")
    val numbers = sortedSetOf<String>()
    repeat(100) {
        numbers.add("Number: $it")
    }

    numbers.forEach(::println)
}