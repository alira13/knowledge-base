package com.example.collections.mutableCollection.variance

fun main() {
    val worker = Container<Worker>(Worker("Some worker"))
    val programmer = Container(Programmer("Some worker"))
    val director = Container(Director("Mike"))
    println(worker)
    println(programmer)
    println(director)
    // in
    copyIn(director, worker)
    //out
    copyOut(director, worker)
}

// по умолчанию src и dst инвариантны. То есть используем только тип T. И заменять на наследников или родителей нельзя
private fun<T> copy(src:Container<T>, dst:Container< T>){
    dst.value = src.value
}

// если добавляем в dst in, то можем добавить дочерний тип или его родителей - контрвариантность
private fun<T> copyIn(src:Container<T>, dst:Container<in T>){
    dst.value = src.value
}

// если добавляем в src out, то можем добавить родительский тип или его наследников - ковариантность
private fun<T> copyOut(src:Container<out T>, dst:Container< T>){
    dst.value = src.value
}

// Производный тип от T
class Container<T>(var value: T? = null)