package com.example.multithreading.supervisorJob

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val job = Job()
private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val exceptionHandler = CoroutineExceptionHandler(
    { _, _ ->
        println("Exception:Smth is wrong")
    })
private val scope = CoroutineScope(job + dispatcher + exceptionHandler)

private val supervisorJob = SupervisorJob()
private val supervisorJobScope = CoroutineScope(supervisorJob + dispatcher + exceptionHandler)

fun main() {
    // вот тут 2 операция отменится, потому что в 1 произошло исключение
    //runOnScope(scope)
    // вот тут 2 операция закончится
    runOnScope(supervisorJobScope)
}

fun runOnScope(scope: CoroutineScope) {
    scope.launch {
        longOperation(2000, 1)
        error("")
    }

    scope.launch {
        longOperation(3000, 2)
    }
}

suspend fun longOperation(timeMillis: Long, num: Int) {
    println("Start long operation $num")
    delay(timeMillis)
    println("Stop long operation $num")
}
