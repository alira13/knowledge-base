package com.example.getterSetter

class Rectangle(private var width: Int, private var height: Int) {

    private val area: Int
        get() {
            return width * height
        }

    override fun toString(): String {
        return "Rectangle(width=$width, height=$height, area=$area)"
    }
}

fun main() {
    println(Rectangle(10, 2))
}