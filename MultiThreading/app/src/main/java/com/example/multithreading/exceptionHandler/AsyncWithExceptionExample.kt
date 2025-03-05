package com.example.multithreading.exceptionHandler

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.async
import java.util.concurrent.Executors

fun main() {
    val obj = AsyncWithExceptionExample()
    // async возвращает Defered, поэтому handler не обрабатывает этот тип, так как он не является типом исключения
    // поэтому job хоть и прекратит все таски дочерние,
    // но handler не увидит исключения и не будет ни ошибки ни нашего сообщения об обшибке от handler
    // Поведение будет одинаковое что с хэндлером что без
    obj.asyncWithError()
    //obj.asyncWithoutError()

    // работает также как и с async
    //obj.asyncTryCatchWithoutHandler()
    //obj.asyncTryCatchWithHandler()

    // как только сделаем async-await, станет работать также как async
    //obj.asyncAwaitWithoutHandler()
    //obj.asyncAwaitWithHandler()
}

class AsyncWithExceptionExample() {
    private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()

    private val rootParentExceptionHandler =
        CoroutineExceptionHandler { _, _ ->
            println("rootParentExceptionHandler:FAIL")
        }


    private val rootScopeWithoutHandler = CoroutineScope(dispatcher)

    // с async позволяет обработать проросшее на самый верх необработанное исключение
    private val rootScopeWithHandler = CoroutineScope(dispatcher + rootParentExceptionHandler)


    // Ситуация 1: rootScopeWithoutHandler
    // здесь программа упадет с ошибкой, так как необработанное исключение проросло наверх
    // и там никем не было обработано
    fun asyncWithError() {
        // через 5 сек должны увидеть печать, но не увидим,
        // потому что корневой job выбросит исключение через 3 сек и остановит все дочерние корутины
        asyncTask(rootScopeWithoutHandler)
        // вот этот метод породит необработанное исключение, оно поднимается до родительского
        // и родительский job отменит все дочерние корутины и упадет с ошибкой(так как и в нем исключение не обработано- без handler)
        asyncTaskWithException(rootScopeWithoutHandler)
    }

    // Ситуация 2: scopeWithHandler
    // здесь программа НЕ упадет с ошибкой, так как необработанное исключение проросло наверх
    // и там оно БЫЛО обработано exceptionHANDLER
    fun asyncWithoutError() {
        // через 5 сек должны увидеть печать, но не увидим,
        // потому что корневой job выбросит исключение через 3 сек и остановит все дочерние корутины
        asyncTask(rootScopeWithHandler)
        // вот этот метод породит необработанное исключение, оно поднимается до родительского
        // и родительский job отменит все дочерние корутины и но НЕ УПАДЕТ с ошибкой(так как и в нем исключение обработано- с handler)
        asyncTaskWithException(rootScopeWithHandler)
    }

    private fun asyncTask(scope: CoroutineScope) {
        scope.async {
            delay(5000)
            println("Do smth in someTask")
        }
    }

    private fun asyncTaskWithException(scope: CoroutineScope) {
        scope.async {
            println("Do smth in taskWithException")
            delay(3000)
            throw RuntimeException("asyncTaskWithException exception")
        }
    }


    private fun asyncAwaitTask(scope: CoroutineScope) {
        val job = scope.async {
            delay(5000)
            println("Do smth in someTask")
        }

    }

    private fun asyncAwaitTaskWithException(scope: CoroutineScope) {
        val job = scope.async {
            println("Do smth in taskWithException")
            delay(3000)
            throw RuntimeException("asyncTaskWithException exception")
        }

    }

    fun asyncAwaitWithoutHandler() {
        asyncAwaitTaskWithException(rootScopeWithoutHandler)
        asyncAwaitTask(rootScopeWithoutHandler)
    }

    fun asyncAwaitWithHandler() {
        asyncAwaitTaskWithException(rootScopeWithHandler)
        asyncAwaitTask(rootScopeWithHandler)
    }

    // вот тут пофиг на хэндлер, потому что в дочерней корутине уже обработано исключение, оно тупо не дойдет до root,
// поэтому и другие корутины не отменятся и сообще в другой таске без исключения пропечатается
// и что try-catch, что runCatching работают одинаково, разницы нет
    fun asyncTryCatchWithoutHandler() {
        asyncTaskRunCachingException(rootScopeWithHandler)
        asyncTask(rootScopeWithoutHandler)
    }

    fun asyncTryCatchWithHandler() {
        asyncTaskRunCachingException(rootScopeWithHandler)
        asyncTask(rootScopeWithoutHandler)
    }

    private fun asyncTaskTryCatchException(scope: CoroutineScope) {
        scope.async {
            try {
                println("asyncTaskTryCatchException")
                delay(2000)
                throw RuntimeException("asyncTaskTryCatchException exception")
            } catch (ex: Exception) {
                println("FAIL")
            }
        }
    }

    // тоже самое что и try-catch
    private fun asyncTaskRunCachingException(scope: CoroutineScope) {
        scope.async {
            runCatching {
                println("asyncTaskRunCachingException")
                delay(3000)
                throw RuntimeException("My exception")
            }
                .onSuccess { println("OK") }
                .onFailure { println("FAIL") }
        }
    }
}

