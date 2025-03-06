package com.example.multithreading.thread

import java.lang.Thread.sleep
import kotlin.concurrent.thread

fun main() {
    thread(name = "Simpliest") {
        repeat(5) {
            println("*")
            sleep(1000)
        }
    }

    // окончит свою работу, после того
    // как все основные потоки завершатся,
    // сам дальше не продолжит работу
    thread(isDaemon = true) {
        while (true) {
            println("I am daemon")
            sleep(1000)
        }
    }
}