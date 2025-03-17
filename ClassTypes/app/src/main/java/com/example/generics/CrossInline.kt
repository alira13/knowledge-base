package com.example.generics

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

// crossinline - другой контекст выполнения, а значит
// мы не можем внутри crossinline сделать non-local return
// не можем внутри crossinline вызывать suspend-функцию,так как она будет находиться вне корутины
private fun main() {
    CoroutineScope(Dispatchers.IO).launch {
        doSmth {
            println("Command1")
            // не получится так как мы указали crossinline, что у нее другой контекст выполнения и она не будет вызвана в рамках этой корутины
            //delay(1000)
            // нельзя потому что crossinline
            // return
        }
    }
}

// crossinline указывается у lambda-параметра функции, которая принимает lambda,
// выполняющуюся в другом контексте, например в другом потоке, котрутине или анонимном классе
// без добавления crossinline не будет работать, так как может содержать non-local return
// если хотим сделать функцию inline, тогда нам нужно пометить,
// что у command1 другой контекст выполнения - а значит нелокальный return нельзя будет сделать
private inline fun doSmth(crossinline command1: () -> Unit) {
    thread {
        command1()
    }
}
