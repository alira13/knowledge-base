package com.example.collections.mutableCollection.typesErasing

private fun main() {
    val b1 = Box(10)
    val b2 = Box(20)
    val bv = b1.value as Int + b2.value as Int
    println(bv)

    // По сути
    val p1 = ParamBox(30)
    val p2 = ParamBox(50)
    val pv = p1.value + p2.value
    println(pv)

    doSmth(p1)
}

// при декомпиляции работают одинаково. value являются Object - то есть его тип стирается,а затем приводятся к нужному типу.
private class Box(val value: Any?)

//Дженерик это просто синтаксический сахар, в котором приведение к типу происходит под капотом
private class ParamBox<T>(val value: T)

// c производным типа T не будет работать, потому что будет стирание типов
private fun <T> doSmthWithList(el: List<T>) {
    /*when (el) {
        is List<String> -> println("This is String")
        is List<Box> -> println("This is Box")
        else -> println("I dont know")
    }
     */
}
// c исходным типом T все получается, проверка работает
private fun <T> doSmth(el: T) {
    when (el) {
        is String -> println("This is String")
        is Box -> println("This is Box")
        else -> println("I dont know")
    }
}