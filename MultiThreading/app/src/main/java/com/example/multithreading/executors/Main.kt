package com.example.multithreading.executors

import java.util.concurrent.Executors
import java.util.concurrent.RunnableFuture
import kotlin.concurrent.thread

fun main() {
    //runWithThread()
    runWithExecutor()
}

data class Image(val id: Int)

private fun runWithThread(){
    repeat(10_000) {
        // 1 проблема создание thread занимает немало времени и оперативной операции
        // когда потоков много, может случиться ex OutOfMemError

        // 2 проблема когда нужно что-то с потоками сделать, например отменить,
        // их нужно сохранить в переменную в список и затем проходиться по ним.
        // Управление жизненным циклом потока становится сложным - для этого созданы executorService

        thread {
            workWithImage(Image(it))
        }
    }
}

private fun runWithExecutor(){
    // создаем количество потоков по количеству ядер
    // для сложных вычислений
    val executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
    repeat(10_000) {
        executor.execute {
            workWithImage(Image(it))
        }
    }
}
private fun workWithImage(image: Image) {
    println("Working with $image")
    Thread.sleep(1000)
    println("Done")
}