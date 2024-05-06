package com.example.coroutines.timer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    init {
        Log.i("COROUTINE", "Старт таймера")
        viewModelScope.launch {
            runTimer(20)
            Log.i("COROUTINE", "Окончание таймера")
        }
        Log.i("COROUTINE", "Какое-то рандомное время")
    }

    private suspend fun runTimer(timeInSeconds: Int) {
        var result = 1
        for (i in timeInSeconds..0) {
            result += 1
            Log.i("COROUTINE", result.toString())
            delay(1000)
        }
    }
}