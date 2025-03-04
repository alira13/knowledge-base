package com.example.lyskovSubstitution

// даже без интерфейса уже соблюдатется принцип
interface Figure {
    fun getArea(): Int
}

open class RectangleRight(private val width: Int, private val height: Int) : Figure {
    override fun getArea(): Int {
        return width * height
    }
}

class SquareRight(side: Int) : RectangleRight(side, side), Figure

fun main() {
    val square = SquareRight(10)
    val rectangle = RectangleRight(5, 20)
    testFigure(square)
    testFigure(rectangle)
}

fun testFigure(figure: RectangleRight) {
    println(figure.getArea() == 100)
}