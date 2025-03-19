package com.example.designpatterns.builder

fun main() {
    // создем билдер и передаем в него нужные свойства, затем вызываем build
    val product = Product.Builder()
        .name("Smartphone")
        .price(999.99)
        .manufacturer("TechCorp")
        .warranty(24)
        .build()

    println(product)
}

// чтобы каким-то неправильным методом не создать Product и
// только 1 вариант был через билдер - делаем конструктор приватным
private data class Product private constructor(
    private val name: String,
    private val price: Double,
    private val manufacturer: String,
    private val warranty: Int
) {
    class Builder() {
        // чтобы нельзя было изменить когда создаем список, мы через методы устанавливаем наши свойства
        // здесь можем выставить значения по умолчанию
        private var name: String = ""
        private var price: Double = 0.0
        private var manufacturer: String = ""
        private var warranty: Int = 0

        // для функционального стиля вызова возвращает Builder
        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun price(price: Double): Builder {
            this.price = price
            return this
        }

        fun manufacturer(manufacturer: String): Builder {
            this.manufacturer = manufacturer
            return this
        }

        fun warranty(months: Int): Builder {
            this.warranty = months
            return this
        }

        // создаем объект
        fun build(): Product {
            return Product(this.name, this.price, this.manufacturer, this.warranty)
        }
    }
}