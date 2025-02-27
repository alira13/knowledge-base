package com.example.multithreading.thread

import java.lang.Thread.sleep
import kotlin.concurrent.thread

class ThreadRunner {
    fun runThreads(): Map<String, String> {
        val threadInfo = mutableMapOf<String, String>()

        // Добавьте в Map имя главного потока и описание его работы
        threadInfo[Thread.currentThread().name] = "Главный поток, который управляет выполнением"
        // Запустите три потока, добавляя в Map имя потока и описание его работы
        val thread1 = thread {
            sleep(2000)
            threadInfo[Thread.currentThread().name] = "Выполняет вычисления 1"
        }
        val thread2 = thread {
            threadInfo[Thread.currentThread().name] = "Выполняет вычисления 2"
        }
        val thread3 = thread {
            threadInfo[Thread.currentThread().name] = "Выполняет вычисления 3"
        }
        // Дождитесь завершения потоков, чтобы они успели записать свои имена в Map
        thread1.join()
        thread2.join()
        thread3.join()
        return threadInfo
    }
}

fun main() {
    println(ThreadRunner().runThreads())
}