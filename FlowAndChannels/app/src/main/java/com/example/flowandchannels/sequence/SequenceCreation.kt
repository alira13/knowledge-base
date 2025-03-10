package com.example.flowandchannels.sequence

import kotlin.random.Random

private fun main() {
    val seq = createViaAsSequence()
    doSmth(seq)
}

// излишне создавать список 1000 элементов если нужно только 10
private fun createViaAsSequence(): Sequence<Int> {
    val list = mutableListOf<Int>().apply {
        repeat(1000) {
            add(it)
        }
    }
    return list.asSequence()
}

// может никогда не закончиться, если нет стоп-функций типа take
private fun createViaGenerateWithInitValue(): Sequence<Int> {
    val seq = generateSequence(0) {
        it + 1
    }
    return seq
}

// может никогда не закончиться, если нет стоп-функций типа take
private fun createViaGenerate(): Sequence<Int> {
    val seq = generateSequence {
        Random.nextInt()
    }
    return seq
}

// самый гибкий, можно любой код внутри выполнять
private fun createViaSequence(): Sequence<Int> {
    val seq = sequence<Int> {
        println("Generate item")
        Random.nextInt()
        // отправит значение 0 по цепочке и приостановит генерацию пока не дойдет по цепочке до конца
        yield(0)
        repeat(100) { Random.nextInt() }
    }
    return seq
}

private fun doSmth(seq: Sequence<Int>) {
    var filterCount = 0
    var mapCount = 0
    seq.filter {
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