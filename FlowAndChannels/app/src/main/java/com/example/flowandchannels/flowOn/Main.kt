package com.example.flowandchannels.flowOn

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

private fun main() {
    //val flow = emitOnProducer()
    collectOnSubscriber()
}

private fun emitOnProducer(): Flow<Int> {
    return flow {
        repeat(5) {
            println("Emitted: ${getThreadName()} $it")
            emit(it)
            delay(1000)
        }
    }
}

private fun collectOnSubscriber() {
    scope.launch {
        emitOnProducer()
            .onStart { println("onStart: ${getThreadName()}") }
            //emit  - на самом деле тут поэтому на него будет влиять flowOn тоже
            .flowOn(Dispatchers.IO)
            .flowOn(Dispatchers.Default)
            .onEach { println("onEach: ${getThreadName()} $it") }
            .map {
                println("map: ${getThreadName()} $it")
                it
            }
            .collect {
                println("collect: ${getThreadName()} $it")
            }
    }
}

private fun getThreadName() = Thread.currentThread().name