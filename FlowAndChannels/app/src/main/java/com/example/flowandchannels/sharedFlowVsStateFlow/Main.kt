package com.example.flowandchannels.sharedFlowVsStateFlow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import kotlin.concurrent.thread


private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

private val sharedFlowAsStateFlow = MutableSharedFlow<Int>(
    replay = 1, // количество элементов для новых subscribers
    extraBufferCapacity = 0, // то есть в буфере всегда будет хранится только 1 значение из replay
    onBufferOverflow = BufferOverflow.DROP_OLDEST // только последнее актуальное будет collect, все остальные значения будут игнорироваться
)//+ не может быть повторяющихся элементов

private val sharedFlow = MutableSharedFlow<Int>()
private val stateFlow = MutableStateFlow(0)

fun main() {
    //emitOnCoroutine()

    sharedFlow.onEach {
        delay(1000)
        println("shared: $it")
    }.launchIn(scope)

    stateFlow.onEach {
        delay(1000)
        println("state: $it")
    }.launchIn(scope)

    emitWithoutCoroutine()
}

private fun emitWithoutCoroutine() {
    Thread.sleep(2000)
    thread {
        repeat(100) {
            sharedFlow.tryEmit(it)
        }
    }
    thread {
        repeat(100) {
            stateFlow.tryEmit(it)
        }
    }
}

private fun emitOnCoroutine() {
    scope.launch {
        repeat(100) {
            sharedFlow.emit(it)
        }
    }

    scope.launch {
        repeat(100) {
            stateFlow.emit(it)
        }
    }
}