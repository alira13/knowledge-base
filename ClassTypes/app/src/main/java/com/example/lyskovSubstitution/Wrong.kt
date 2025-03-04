package com.example.lyskovSubstitution

open class Rectangle(private var width: Int, private var height: Int) {
    open fun getWidth(): Int {
        return width
    }

    open fun getHeight(): Int {
        return height
    }

    // не очень история делать мутабельные поля и устанавливать их извне,
    // ведь когда мы меняем ширину, по сути это уже другой прямоугольник

    open fun setWidth(width: Int) {
        this.width = width
    }

    open fun setHeight(height: Int) {
        this.height = height
    }

    open fun getArea(): Int {
        return width * height
    }
}

class Square(private var side: Int) : Rectangle(side, side) {

    override fun setWidth(width: Int) {
        super.setWidth(width)
        super.setHeight(width)
    }

    override fun setHeight(height: Int) {
        super.setWidth(height)
        super.setHeight(height)
    }
}

fun main() {
    val rectangle = Rectangle(10, 10)
    testFigure(rectangle)
    val square = Square(10)
    testFigure(square)
}

fun testFigure(figure:Rectangle){
    // вот эти функции все ломают
    figure.setWidth(20)
    figure.setHeight(5)
    println(figure.getArea() == 100)
}