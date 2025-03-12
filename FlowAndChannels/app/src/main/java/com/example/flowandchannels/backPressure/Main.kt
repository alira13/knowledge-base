package com.example.flowandchannels.backPressure

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

private fun main() {
    // produser

    // по умолчанию используется suspend - когда элемент заэмитится
    // и следующий не будет эмититься пока предыдущий не collect

    // можем добавить buffer и тогда наэмитится по размеру буфера(размер 64 по умолчанию) число элементов,
    // остановится при переполнении и будет ждать collect

    // есть разные стратегии при переполнении буфера
    // DROP_SUSPEND - эмит ожидает, когда collect обработает и потом производит следующий эмит
    // DROP_LATEST - collect хватает последовательно следующий эмит, а все эмиты, которые не успели дойти до collect - удаляются
    // DROP_OLDEST - collect хватает последний заэмиченный, а все остальные до ПРОПАДАЮТ
    val flow = flow<Int> {
        repeat(10) {
            println("Emitted $it")
            emit(it)
            println("After emit $it")
            delay(100)
        }
    }.buffer(capacity = 2, BufferOverflow.DROP_LATEST)

    //consumer
    scope.launch {
        flow.collect() {
            //backpressure потребитель обрабатывает данные дольше, чем создатель создает
            println(">>>Collected $it")
            delay(1000)
            println(it) }
    }
}
