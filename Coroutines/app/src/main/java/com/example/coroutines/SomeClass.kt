package com.example.coroutine_example

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SomeClass {

    init {
        GlobalScope.launch {
            Log.i("CONTINUATION", "Старт корутин ${Thread.currentThread().name}")
            notSuspendPrint()
            suspendPrint()
            Log.i("CONTINUATION", "Конец корутин ${Thread.currentThread().name}")
        }
    }

    private fun notSuspendPrint() {
        Log.i("CONTINUATION", "Не suspend-функция ${Thread.currentThread().name}")
    }

    private suspend fun suspendPrint() {
        Log.i("CONTINUATION", "suspend-функция ${Thread.currentThread().name}")
    }
}