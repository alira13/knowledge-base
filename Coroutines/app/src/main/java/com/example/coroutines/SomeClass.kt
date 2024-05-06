package com.example.coroutine_example

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SomeClass {

    init {
        GlobalScope.launch {
            Log.i("CONTINUATION", "Старт корутин")
            notSuspendPrint()
            suspendPrint()
            Log.i("CONTINUATION", "Конец корутин")
        }
    }

    private fun notSuspendPrint() {
        Log.i("CONTINUATION", "Не suspend-функция")
    }

    private suspend fun suspendPrint() {
        Log.i("CONTINUATION", "suspend-функция")
    }
}