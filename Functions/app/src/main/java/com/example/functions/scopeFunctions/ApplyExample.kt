package com.example.functions.scopeFunctions

import android.graphics.Color


fun main() {
    // Пример 1: Настройка объекта с помощью прямых вызовов
    val labelDirect = Label()
    labelDirect.text = "Hello, World!"
    labelDirect.font = "16f"
    labelDirect.foreground = Color.BLUE

    // Пример 2: Настройка объекта с помощью функции `with`
    val labelWith = Label()
    with(labelWith) {
        text = "Hello, World!"
        font = "16f"
        foreground = Color.BLUE
    }

    // Пример 3: Настройка объекта с помощью функции `also`
    val labelAlso = Label().also { label ->
        label.text = "Hello, World!"
        label.font = "16f"
        label.foreground = Color.BLUE
    }

    // Пример 4: Настройка объекта с помощью функции `let`
    val labelLet = Label().let { label ->
        label.text = "Hello, World!"
        label.font = "16f"
        label.foreground = Color.BLUE
        label // Возвращаем объект
    }

    // Пример 5: Настройка объекта с помощью функции `apply`
    val labelApply = Label().apply {
        text = "Hello, World!"
        font = "16f"
        foreground = Color.BLUE
    }

    println(labelDirect)
    println(labelWith)
    println(labelAlso)
    println(labelLet)
    println(labelApply)
}

private class Label(){
    var text:String = ""
    var font:String = ""
    var foreground: Int = Color.BLUE
}