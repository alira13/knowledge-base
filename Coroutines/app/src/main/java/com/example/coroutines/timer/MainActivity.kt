package com.example.coroutine_example.timer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.coroutines.R
import com.example.coroutines.timer.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    init {
        for (i in 0..100) {
            lifecycleScope.launch {
                countNumber()
            }
        }
    }

    private suspend fun countNumber() {
        (1..10).forEach {
            println("Значение: $it")
            delay(1000) // Приостанавливаем текущий поток на 1 секунду
        }
    }
}