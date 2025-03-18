package com.example.destructure

private fun main() {
    val map = mapOf(
        Pair("Hello", "Bonjor"),
        Pair("Thank you", "Merci")
    )

    val list = listOf(
        MyPair("Hello", "Bonjor"),
        MyPair("Thank you", "Merci")
    )

    // деструктуризация, когда кл
    for ((en, fr) in list) {
        println("$en-$fr")
    }
}
// data классы дают возможность деструктуризации за счет генерации методов Component1 Component2
// которые вызываются при вызове деструктурирующего оператора
private data class MyPair(val english: String, val french: String)