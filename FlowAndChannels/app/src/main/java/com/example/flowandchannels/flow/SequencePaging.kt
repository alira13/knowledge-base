package com.example.flowandchannels.flow

// пример для пагинации на sequence

private fun main() {
    sequence<List<String>> {
        repeat(5) {
            val items = loadNext()
            yield(items)
        }
    }.forEach { scroll(it) }
}

private var lastIndex = 0
private fun loadNext(): List<String> {
    println("Loading...")
    Thread.sleep(2000)
    return (lastIndex..<lastIndex + 10).map {
        "Video $it"
    }.also {
        lastIndex += 10
        println("Finish loading... ${it.joinToString()}")
    }
}

private fun scroll(items: List<String>) {
    println("Scroll...")
    Thread.sleep(100L * items.count())
    println("Finish scrolling... ${items.joinToString()}")
}