package com.example.flowandchannels.hotColdShareIn

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

object Timer {
    private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
    private val scope = CoroutineScope(dispatcher)

    // создаем горячий поток и не разрешаем в него снаружи эмитить, только внутри таймера
    val sharedTimerFlow = MutableSharedFlow<Int>().apply {
        scope.launch {
            getTimerFlow().collect {
                emit(it)
            }
        }
    }.asSharedFlow()

    // холодный поток
    val nonSharedTimerFlow = getTimerFlow()


    // преобразование холодного в горячий
    // Started - когда нужно начинать эмитить данные у холодного потока
    //  - Eagerly - когда объект создан, сразу эмит
    //  - Lazily - пока не появится подписчик первый, старта не будет
    //  - WhileSubscribed - эмитит значения при первой подписке,
    //  но закончит эмитить когда не станет подписчиков
    val shareInFlow = getTimerFlow().shareIn(scope, SharingStarted.Lazily)

    // стартуем холодный поток
    private fun getTimerFlow(): Flow<Int> {
        return flow<Int>() {
            var seconds = 0
            while (true) {
                println("Emitted $seconds")
                emit(seconds++)
                delay(1000)
            }
        }
    }
}