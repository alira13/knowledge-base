package com.example.multithreading.cancel


import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import kotlin.coroutines.coroutineContext

fun main() {
    val obj = Cancel()
    obj.runTimer()
}

class Cancel {

    private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
    private val scope = CoroutineScope(dispatcher)

    // Нам самим надо позаботиться о том, чтобы отменить корутину
    // 1 выставить флаг isActive = false(cancel())
    // 2 нужен источник CancellationException для scope(библиотечные методы или наши механизмы)
    // 3 пропустить CancellationException в try-catch

    fun runTimer() {
        val job = scope.launch {
            timer()
        }

        // ждем 3сек
        Thread.sleep(3000)
        // отмена корутины в scope
        job.cancel()
    }

    private suspend fun timer() {
        var seconds = 0
        while (true) {
            try {
                // источник 1
                //if (!coroutineContext.isActive) throw CancellationException()
                // источник 2(аналог предыдущего)
                coroutineContext.ensureActive()

                println(">>>")
                println(seconds++)

                // не источник
                Thread.sleep(1000)
            }
            // пропустить CancellationException
            catch (c: CancellationException) {
                println("CancellationException")
                throw c
            } catch (e: Exception) {

            }
        }
    }
}