package com.example.flowandchannels.hotFlows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

// создали здесь, а можем эмитить в любом месте
private var myFlow = MutableSharedFlow<Int>()

private fun main() {
    //notEmitOnlyUsedItems()
    //emitWhenHaveNotSubscribers()
    //dontStopWhenStopEmitting()
    notStartNewFlowForEachSubscriber()
}

private fun notStartNewFlowForEachSubscriber() {
// первый подписчик
    scope.launch {
        getTimerFlow()
    }
    scope.launch {

        // в flow эмит произодет 10 раз в цикле и затем flow завершит работу
        myFlow.collect { println("First subscriber $it") }
    }

// второй подписчик получит данные с 5 уже
    scope.launch {
        delay(5000)
        // в flow эмит произодет 10 раз в цикле и затем flow завершит работу
        myFlow.collect { println("Second subscriber $it") }
    }
}

// НЕ заканчивает работу, когда остановится emit
private fun dontStopWhenStopEmitting() {
    scope.launch {
        getTimerFlow()
        //хоть у нас и в цикле 10 раз эмитятся, все равно поток не остановится,
        // потому что кто-то может докинуть еще туда
        myFlow.collect { println("First subscriber $it") }
        // этот код не напечатается
        println("Finish flow")
    }
}

private fun notEmitOnlyUsedItems() {
    scope.launch {
        getTimerFlow()
        // заэмитит все 10 значений
        myFlow.take(5).collect { }
    }
}

private fun emitWhenHaveNotSubscribers() {
    scope.launch {
        getTimerFlow()
        // flow.filter - не терминальный оператор. Но Hot Flow будет эмитить данные.
        // Увидим печать
        myFlow.filter { it % 2 == 0 }
    }
}

private suspend fun getTimerFlow() {
    var seconds = 0
    repeat(10) {
        println("Emit $seconds")
        myFlow.emit(seconds++)
        delay(1000)
    }
}