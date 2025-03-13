package com.example.flowandchannels.exceptionsInFlow

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

private val exceptionHandler =
    CoroutineExceptionHandler { _: CoroutineContext, _: Throwable ->
        println("Caught in handler")
    }
private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher + exceptionHandler)

fun main() {
    //val flow = emitFromProducer()
    //collectBySubscriberExceptionHandler(flow)
    //collectBySubscriber(flow)

    //val wrongFlow = emitFromProducerWithTryCatchWRONG()
    //collectBySubscriberExceptionHandler(wrongFlow)
    //collectBySubscriber(wrongFlow)

    val flow = emitFromProducer()
    collectBySubscriberWithRetry(flow)
}

fun collectBySubscriberWithRetry(flow: Flow<Int>) {
    scope.launch {
        flow
            .retry(3) { it is Exception }
            .catch {
                println("Caught in subsriber in catch: ${it.message}")
            }
            .collect {
                println("Collect:$it")
            }
    }
}

fun collectBySubscriber(flow: Flow<Int>) {
    scope.launch {
        try {
            flow.collect {
                println("Collect:$it")
            }
        } catch (ex: Exception) {
            println("Caught ${ex.message}")
        }
    }
}

fun collectBySubscriberExceptionHandler(flow: Flow<Int>) {
    scope.launch {
        flow.collect {
            println("Collect:$it")
        }
    }
}

fun emitFromProducer(): Flow<Int> {
    return flow<Int> {
        repeat(3) {
            emit(it)
            println("Emitted:$it")
        }
        throw Exception("Exception in flow: internet connection error")
        repeat(5) {
            emit(it + 3)
            println("Emitted:$it")
        }
    }
}


fun emitFromProducerWithTryCatchWRONG(): Flow<Int> {
    return flow<Int> {
        try {
            repeat(5) {
                emit(it)
                println("Emitted:$it")
                //...
                // вот тут на самом деле на каждой итерации вызывается collect
                // если на каком-то этапе у collect произойдет ошибка, то этот try-catch также ее сожрет
                // ..collect
            }
            throw Exception("Exception in flow: internet connection error")
            repeat(5) {
                emit(it)
                println("Emitted:$it")

            }
        } catch (ex: Exception) {
            // сожрали ошибку и никто ее дальше не увидит и не обработает
            println("WRONG EXAMPLE: Exception caught in producer")
        }
    }
}