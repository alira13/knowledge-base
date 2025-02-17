package com.example.multithreading

import java.lang.Thread.sleep
import kotlin.concurrent.thread
import kotlin.random.Random.Default.nextInt

/*fun main() {
    /*  thread {
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

     */
    runGame()
}

 */

fun main() {
    println("Enter num")
    val userNum = readln().trim().toInt()
    var win = false

    //timer
    thread {
        var i = 1
        while (!win) {
            println(i++)
            sleep(1000)
        }
    }

    thread {
        while (true) {
            val compNum = nextInt(0, 1_000_000_001)
            if (userNum == compNum) {
                println("I win. Your number is $compNum")
                win = true
                break
            }
        }
    }
}