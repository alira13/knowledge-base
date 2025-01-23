package com.example.coroutines.simpliestMultithread

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.coroutines.R
import kotlinx.coroutines.Runnable
import kotlin.concurrent.thread
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    init {
        runMultithreadGame()
        //runMultiByKotlin()
        //runSeqByJava()
        //runMultiByJava()
    }

    private fun runMultiByJava() {
        // 1 создаем новый поток
        Log.d("MY", "create thread")
        val secondThread = Thread(object : Runnable {
            override fun run() {
                Log.d("MY", "start inside run myThread thread")
                repeat(1000) {
                    Log.d("MY", "Print * ${Thread.currentThread().name}")
                }
            }
        })
        // 2 меняем имя по умолчанию
        Log.d("MY", "set thread name")
        secondThread.name = "myThread"

        // 3 запускаем поток
        Log.d("MY", "start myThread thread")
        secondThread.start()

        // 1 main-поток
        Log.d("MY", "start fun in main thread")
        repeat(1000) {
            Log.d("MY", "Print + ${Thread.currentThread().name}")
        }
    }

    private fun runSeqByJava() {
        //в main действия выполняются последовательно

        // 1 Запускаем действия в main
        // main-поток
        repeat(100) {
            Log.d("MY", "Print + ${Thread.currentThread().name}")
        }

        // 2 создаем новый поток, но само создание происходит все еще в main
        val secondThread = Thread(object : Runnable {
            override fun run() {
                // а вот тут туже все происходит в другом потоке
                repeat(100) {
                    Log.d("MY", "Print * ${Thread.currentThread().name}")
                }
            }
        })
        // 3 меняем имя по умолчанию
        secondThread.name = "myThread"
        // 4 запускаем поток
        secondThread.start()
    }

    private fun runMultiByKotlin() {
        thread(name = "myNewThreadByKotlin") {
            repeat(100) {
                Log.d("MY", "Print * ${Thread.currentThread().name}")
            }
        }

        repeat(100) {
            Log.d("MY", "Print - ${Thread.currentThread().name}")
            Thread.sleep(1000L)
        }
    }

    private fun runMultithreadGame() {
        val guestedNum = 1234

        var win = false

        thread(name = "timerThread") {
            var seconds = 1
            while (!win) {
                Log.d("MY", "${seconds++}")
                Thread.sleep(1000L)
            }
        }

        thread(name = "calcThread") {
            while (true) {
                val randomVal: Int = Random.nextInt(0, 100_000_000)
                if (randomVal == guestedNum) {
                    Log.d(
                        "MY",
                        "${Thread.currentThread().name} - I win. Your num is $randomVal"
                    )
                    win = true
                    break
                }
            }
        }
    }
}