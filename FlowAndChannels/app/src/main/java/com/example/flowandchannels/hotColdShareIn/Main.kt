package com.example.flowandchannels.hotColdShareIn

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

private fun main() {
    //collectFlow(Timer.nonSharedTimerFlow)
    //collectFlow(Timer.sharedTimerFlow)
    collectFlow(Timer.shareInFlow)
}

private fun collectFlow(flow:Flow<Int>){
    scope.launch {
        delay(2000)
        flow.collect() { println("First subscriber $it") }
    }

    scope.launch {
        delay(5000)
        flow.collect() { println("Second subscriber $it") }
    }
}