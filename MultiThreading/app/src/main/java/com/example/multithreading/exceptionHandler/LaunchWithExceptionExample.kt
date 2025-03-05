package com.example.multithreading.exceptionHandler

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

fun main() {
    val obj = LaunchWithExceptionExample()

    // никто не словит исключение, оно прорастет в корневой job и там будет ошибкка(RTE), все остальные корутины отменятся после исключения
    obj.launchWithoutHandler()
    // ошибка прорастет до корневого job и хэндлер там обработает ее, все остальные корутины отменятся после исключения
    obj.launchWithHandler()

    // ловит исключнеие в дочерней корутине, обрабатывает ее
    // это исключение не прорастает в родительскую и поэтому остальные корутины не прерываются
    obj.launchTryCatchWithoutHandler()
    obj.launchTryCatchWithHandler()
}

class LaunchWithExceptionExample() {
    private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()

    private val rootParentExceptionHandler =
        CoroutineExceptionHandler { _, _ ->
            println("rootParentExceptionHandler:FAIL")
        }

    private val parentExceptionHandler =
        CoroutineExceptionHandler { _, _ ->
            println("parentExceptionHandler:FAIL")
        }

    private val childExceptionHandler =
        CoroutineExceptionHandler { _, _ ->
            println("childExceptionHandler:FAIL")
        }

    private val rootScopeWithoutHandler = CoroutineScope(dispatcher)

    // с launch позволяет обработать проросшее на самый верх необработанное исключение
    private val rootScopeWithHandler = CoroutineScope(dispatcher + rootParentExceptionHandler)

    // Ситуация 1: rootScopeWithoutHandler
// здесь программа упадет с ошибкой, так как необработанное исключение проросло наверх и там никем не было обработано
    fun launchWithoutHandler() {
        // через 5 сек должны увидеть печать, но не увидим,
        // потому что корневой job выбросит исключение через 3 сек и остановит все дочерние корутины
        launchTask(rootScopeWithoutHandler)
        // вот этот метод породит необработанное исключение, оно поднимается до родительского
        // и родительский job отменит все дочерние корутины и упадет с ошибкой(так как и в нем исключение не обработано- без handler)
        launchTaskWithException(rootScopeWithoutHandler)
    }

    // Ситуация 2: scopeWithHandler
// здесь программа НЕ упадет с ошибкой, так как необработанное исключение проросло наверх и там оно БЫЛО обработано exceptionHANDLER
    fun launchWithHandler() {
        // через 5 сек должны увидеть печать, но не увидим,
        // потому что корневой job выбросит исключение через 3 сек и остановит все дочерние корутины
        launchTask(rootScopeWithHandler)
        // вот этот метод породит необработанное исключение, оно поднимается до родительского
        // и родительский job отменит все дочерние корутины и но НЕ УПАДЕТ с ошибкой(так как и в нем исключение обработано- с handler)
        launchTaskWithException(rootScopeWithHandler)
    }

    private fun launchTask(scope: CoroutineScope) {
        scope.launch {
            delay(5000)
            println("Do smth in someTask")
        }
    }

    private fun launchTaskWithException(scope: CoroutineScope) {
        scope.launch {
            println("Do smth in taskWithException")
            delay(3000)
            throw RuntimeException("LaunchTaskWithException exception")
        }
    }

    // вот тут пофиг на хэндлер, потому что в дочерней корутине уже обработано исключение, оно тупо не дойдет до root,
// поэтому и другие корутины не отменятся и сообще в другой таске без исключения пропечатается
// и что try-catch, что runCatching работают одинаково, разницы нет
    fun launchTryCatchWithoutHandler() {
        launchTaskRunCachingException(rootScopeWithHandler)
        launchTask(rootScopeWithoutHandler)
    }

    fun launchTryCatchWithHandler() {
        launchTaskRunCachingException(rootScopeWithHandler)
        launchTask(rootScopeWithoutHandler)
    }

    private fun launchTaskTryCatchException(scope: CoroutineScope) {
        scope.launch {
            try {
                println("launchTaskTryCatchException")
                delay(2000)
                throw RuntimeException("launchTaskTryCatchException exception")
            } catch (ex: Exception) {
                println("FAIL")
            }
        }
    }

    // тоже самое что и try-catch
    private fun launchTaskRunCachingException(scope: CoroutineScope) {
        scope.launch {
            runCatching {
                println("launchTaskRunCachingException")
                delay(3000)
                throw RuntimeException("My exception")
            }
                .onSuccess { println("OK") }
                .onFailure { println("FAIL") }
        }
    }
}

