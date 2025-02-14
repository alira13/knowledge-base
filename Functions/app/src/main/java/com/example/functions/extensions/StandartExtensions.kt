package com.example.functions.extensions

/**
 * Класс для представления информации о продукте.
 *
 * @property id Уникальный идентификатор продукта.
 * @property name Название продукта.
 * @property price Цена продукта.
 * @property category Категория продукта.
 */
data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val category: String
)

/*
Вам нужно написать метод, который выполнит следующие действия:

Исключит из списка продукты, у которых цена меньше 100.
Оставит только продукты из категорий "Electronics" или "Books".
Отсортирует оставшиеся продукты по цене в порядке возрастания.
Преобразует каждый продукт в строку формата:
"Product ID: <id> | Name: <name> | Price: $<price>".
Вернёт итоговый список строк, который будет отображён в пользовательском интерфейсе.
 */

/**
 * Метод, который обрабатывает список продуктов для отображения в UI.
 *
 * @param products Список продуктов, полученных из репозитория.
 * @return Список строк, готовых для отображения в UI.
 */
fun processProductsForUI(products: List<Product>): List<String> {
    // Реализуйте логику здесь

    val resultList = products
        .filter { it.price >= 100 }
        .filter { it.category == "Electronics" || it.category == "Books" }
        .sortedBy { it.price }
        .map { it -> "Product ID: ${it.id} | Name: ${it.name}  | Price: $${it.price} " }

    return resultList
}

fun main() {
    val products = listOf(
        Product(1, "Laptop", 999.99, "Electronics"),
        Product(2, "Notebook", 12.99, "Books"),
        Product(3, "Tablet", 299.99, "Electronics"),
        Product(4, "Novel", 15.49, "Books"),
        Product(5, "Pen", 2.99, "Stationery")
    )

    val result = processProductsForUI(products)
    println(result)
}