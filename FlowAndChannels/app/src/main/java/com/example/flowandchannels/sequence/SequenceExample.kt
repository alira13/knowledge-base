package com.example.flowandchannels.sequence

private fun main() {
    val list = mutableListOf<Int>().apply {
        repeat(1000) {
            add(it)
        }
    }

    //useList(list)
    useSequence(list)
}

private fun useList(list: MutableList<Int>) {
    var filterCount = 0
    var mapCount = 0
    list.filter {
        println("Take it = $filterCount")
        filterCount++
        it % 2 == 0
    }.map {
        println("Map it = $mapCount")
        mapCount++
        it * 10
    }.take(10).forEach(::println)

    println("Filtered: $filterCount")
    println("Mapped: $mapCount")
}

private fun useSequence(list: MutableList<Int>) {
    var filterCount = 0
    var mapCount = 0
    list.asSequence().filter {
        println("Take it = $filterCount")
        filterCount++
        it % 2 == 0
    }.map {
        println("Map it = $mapCount")
        mapCount++
        it * 10
    }.take(10)
        .forEach { println("item=$it") } // терминальный оператор(без него в последовательности
    // вообще ничего не будет считаться и фильтроваться)

    println("Filtered: $filterCount")
    println("Mapped: $mapCount")
}