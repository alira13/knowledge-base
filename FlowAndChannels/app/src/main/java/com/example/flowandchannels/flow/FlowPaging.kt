package com.example.flowandchannels.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

// пример для пагинации на flow что позволило использовать корутины
private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)
private fun main() {
    scope.launch {
        flow<List<String>> {
            repeat(5) {
                val items = loadNext()
                emit(items)
            }
        }.collect { scroll(it) }
    }
}

private var lastIndex = 0
private suspend fun loadNext(): List<String> {
    println("Loading...")
    delay(2000)
    return (lastIndex..<lastIndex + 10).map {
        "Video $it"
    }.also {
        lastIndex += 10
        println("Finish loading... ${it.joinToString()}")
    }
}

private suspend fun scroll(items: List<String>) {
    println("Scroll...")
    delay(100L * items.count())
    println("Finish scrolling... ${items.joinToString()}")
}