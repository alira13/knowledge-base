package com.example.multithreading.exceptionHandler

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

fun main() {
    val obj = ChildExceptionExample()
    // вот тут в launch На самом деле мы уже сказали что handler будет, просто parent
    // если в launch не указали, то упадет с ошибкой
    // но дочерний не играет роли вообще Не важно launch или launch
    obj.launchWithoutHandler()
    obj.launchWithHandler()
}

class ChildExceptionExample() {
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
    // здесь программа упадет с ошибкой, так как необработанное исключение проросло наверх
    // и там никем не было обработано
    fun launchWithoutHandler() {
        // через 5 сек должны увидеть печать, но не увидим,
        // потому что корневой job выбросит исключение через 3 сек и остановит все дочерние корутины
        launchTask(rootScopeWithoutHandler)
        // вот этот метод породит необработанное исключение, оно поднимается до родительского
        // и родительский job отменит все дочерние корутины и упадет с ошибкой(так как и в нем исключение не обработано- без handler)
        launchTaskWithChildWithException(rootScopeWithoutHandler)
    }

    // Ситуация 2: scopeWithHandler
    // здесь программа НЕ упадет с ошибкой, так как необработанное исключение проросло наверх
    // и там оно БЫЛО обработано exceptionHANDLER
    fun launchWithHandler() {
        // через 5 сек должны увидеть печать, но не увидим,
        // потому что корневой job выбросит исключение через 3 сек и остановит все дочерние корутины
        launchTask(rootScopeWithHandler)
        // вот этот метод породит необработанное исключение, оно поднимается до родительского
        // и родительский job отменит все дочерние корутины и но НЕ УПАДЕТ с ошибкой(так как и в нем исключение обработано- с handler)
        launchTaskWithChildWithException(rootScopeWithHandler)
    }

    private fun launchTask(scope: CoroutineScope) {
        scope.launch(parentExceptionHandler) {
            delay(5000)
            println("Do smth in someTask")
        }
    }
    
    private fun launchTaskWithChildWithException(scope: CoroutineScope) {
        scope.launch(parentExceptionHandler) {
            println("Do smth in taskWithException")
            launch(childExceptionHandler) {
                println("Do smth in child in taskWithException")
                delay(1000)
                throw RuntimeException("child exception")
            }
            delay(3000)
            throw RuntimeException("launchTaskWithException exception")
        }
    }
}

