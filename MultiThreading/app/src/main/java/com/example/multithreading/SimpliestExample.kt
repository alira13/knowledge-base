package com.example.multithreading

import java.lang.Thread.sleep
import kotlin.concurrent.thread
import kotlin.random.Random.Default.nextInt

fun main() {
    thread {
        repeat(1000) {
            println("*")
            sleep(1000)
        }
    }
    repeat(1000)
    {
        println("-")
        sleep(2000)
    }


}


