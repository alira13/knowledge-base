package com.example.anonymousClass

data class Product(val name: String, val price: Int)

abstract class Condition {
    abstract fun isSuitable(product: Product): Boolean
}

fun filterProduct(products: List<Product>, condition: Condition): List<Product> {
    val result: MutableList<Product> = mutableListOf()
    for (product in products) {
        if (condition.isSuitable(product)) {
            result.add(product)
        }
    }
    return result.toList()
}

fun main() {
    val product1 = Product("Soap", 100)
    val product2 = Product("Shower gel", 200)
    val product3 = Product("Cream", 300)
    val product4 = Product("Soup", 50)
    val products = listOf(product1, product2, product3, product4)

    var filtered = filterProduct(products, FilterLikeAnonymousStartsWithS)
    filtered = filterProduct(filtered, FilterLikeAnonymousPriceLow200)

    // заменили реализацию анонимным классом
    // анонимный класс может реализовывать интерфейс или абстрактный класс
    filtered = filterProduct(filtered,
        object : Condition() {
            override fun isSuitable(product: Product): Boolean {
                return product.name.endsWith("up")
            }
        })
    println(filtered)
}


// нам нужен синглтон, поэтому object. По сути синтаксис такой же, как у обычного класса,
// только нет имени у класса, поэтому и анонимный
object FilterLikeAnonymousEndWithUp : Condition() {
    override fun isSuitable(product: Product): Boolean {
        return product.name.endsWith("up")
    }
}

object FilterLikeAnonymousStartsWithS : Condition() {
    override fun isSuitable(product: Product): Boolean {
        return product.name.startsWith('S')
    }
}

object FilterLikeAnonymousPriceLow200 : Condition() {
    override fun isSuitable(product: Product): Boolean {
        return product.price < 200
    }
}

