package com.example.flowandchannels.coldFlows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

private fun main() {
    //emitWhenHaveSubscribers()
    //emitOnlyUsedItems()
    //stopWhenStopEmitting()
    startNewFlowForEachSubscriber()
}

private fun startNewFlowForEachSubscriber() {
    val flow = getTimerFlow()

    scope.launch {
        // в flow эмит произодет 10 раз в цикле и затем flow завершит работу
        flow.collect { println("First subscriber $it") }
    }

// вроде бы объект flow один и кажется, что должны стартануть с 5 у второго подписчика,
// но у холодных потоков для каждого подписчика flow стартует сначала
    scope.launch {
        delay(5000)
        // в flow эмит произодет 10 раз в цикле и затем flow завершит работу
        flow.collect { println("Second subscriber $it") }
    }
}

// заканчивает работу, когда остановится emit
private fun stopWhenStopEmitting() {
    val flow = getTimerFlow()

    scope.launch {
        // в flow эмит произодет 10 раз в цикле и затем flow завершит работу
        flow.collect { }
    }
}

private fun emitOnlyUsedItems() {
    val flow = getTimerFlow()

    scope.launch {
        // заэмитит только 5 значений
        flow.take(5).collect { }
    }
}

private fun emitWhenHaveSubscribers() {
    val flow = getTimerFlow()
    // эмитят данные только при наличии подписчиков(увидим печать)
    /*
    scope.launch {
        // flow.count() - терминальный оператор. Является подписчиком
        val count = flow.count()
        println("count=$count")
    }
     */
    scope.launch {
        // flow.filter - не терминальный оператор. Flow не будет эмитить данные.
        // Тут печани не увидим
        flow.filter { it % 2 == 0 }
    }
}

private fun getTimerFlow(): Flow<Int> {
    return flow<Int>() {
        var seconds = 0
        repeat(10) {
            println(seconds)
            emit(seconds++)
            delay(1000)
        }
    }
}