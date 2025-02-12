package com.example.serialization

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.File

@Serializable
enum class ProductCategory() {
    @SerialName("Clothing")
    CLOTHING,
    @SerialName("Sports")
    SPORTS,
    @SerialName("Electronics")
    ELECTRONICS,
    @SerialName("Home Goods")
    HOME_GOODS,
    @SerialName("Beauty")
    BEAUTY
}

@Serializable
data class Product(
    @SerialName("id") val id: Int,
    @SerialName("product_brand") val productBrand: String,
    @SerialName("product_category") val productCategory: ProductCategory,
    @SerialName("product_availability") val productAvailability: Boolean,
    @SerialName("product_name") val productName: String,
    @SerialName("product_price") val productPrice: Double,
    @SerialName("product_description") val productDescription: String,
    @SerialName("product_rating") val productRating: Double
)

fun loadProducts(jsonContent: String): List<Product> {
    // Замените Any на класс Product
    return Json.decodeFromString(jsonContent)
}

fun main() {
    val file = File("products.json")
    val str = file.readText()
    val products = loadProducts(str)
    println(products)
}