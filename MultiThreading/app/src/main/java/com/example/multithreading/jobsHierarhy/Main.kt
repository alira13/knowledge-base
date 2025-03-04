package com.example.multithreading.jobsHierarhy

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Thread.sleep
import java.util.concurrent.Executors
import kotlin.concurrent.thread

// все диспатчеры используют daemon-потоки
val scopeWithDaemon = CoroutineScope(Dispatchers.Default)

// но если создавать через Executors, pools по умолчанию используют daemon=false
val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
val scopeWithoutDaemon = CoroutineScope(dispatcher)

fun main() {
    //runWithDaemon()
    //runWithoutDaemon()
    //runAndShowParent()
    runAndShowParentWithAnotherContext()
}

fun runWithDaemon() {
    thread {
        repeat(5) {
            println("*")
            sleep(1000)
        }
    }

    // окончит свою работу, после того
    // как все основные потоки завершатся,
    // сам дальше не продолжит работу.
    // Тоже самое если бы запустили через thread(isDaemon=true)
    scopeWithDaemon.launch {
        coroutineContext.job.parent.let { println(it) }
        while (true) {
            println("I am daemon")
            sleep(1000)
        }
    }
}


fun runWithoutDaemon() {
    thread {
        repeat(5) {
            println("*")
            sleep(1000)
        }
    }

    // окончит свою работу, после того
    // как все основные потоки завершатся,
    // сам дальше не продолжит работу.
    // Тоже самое если бы запустили через thread(isDaemon=true)
    scopeWithoutDaemon.launch {
        coroutineContext.job.parent.let { println(it) }
        while (true) {
            println("I am daemon")
            sleep(1000)
        }
    }
}

// у потоков одинаковый parent,
// потому что scope одинаковый с одинаковым context(dispatcher, parentJob, ...)
fun runAndShowParent() {
    scopeWithoutDaemon.launch {
        coroutineContext.job.parent.let { println(it) }
        repeat(5) {
            println("*")
            sleep(1000)
        }
    }

    // окончит свою работу, после того
    // как все основные потоки завершатся,
    // сам дальше не продолжит работу.
    // Тоже самое если бы запустили через thread(isDaemon=true)
    scopeWithoutDaemon.launch {
        coroutineContext.job.parent.let { println(it) }
        while (true) {
            println("I am daemon")
            sleep(1000)
        }
    }
}


// у потоков одинаковый parent,
// потому что scope одинаковый с одинаковым context(dispatcher, parentJob, ...)
fun runAndShowParentWithAnotherContext() {
    scopeWithoutDaemon.launch {
        coroutineContext.job.parent.let { println(it) }
        repeat(5) {
            println("*")
            sleep(1000)
        }
    }

    //  а вот тут мы уже поменяли контекст и будет другая job родительская
    scopeWithoutDaemon.launch {
        coroutineContext.let{
            println(it)
        }
        withContext(Dispatchers.Default){
            //coroutineContext.job.parent.let { println(it) }

            coroutineContext.let{
                println(it)
            }

            while (true) {
                println("I am daemon")
                sleep(1000)
            }
        }
    }
}