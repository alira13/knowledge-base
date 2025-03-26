package com.example.broadcastreceiver

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlin.concurrent.thread

class LoadingService : Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        thread {
            for (i in 1..10) {
                Log.d("MY", "Loading $i")
                Thread.sleep(1000)
                val myIntent = Intent(INTENT_NAME).apply {
                    putExtra(INTENT_DATA, i * 10)
                }
                sendBroadcast(myIntent)
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    companion object{
        const val INTENT_NAME = "loaded"
        const val INTENT_DATA = "percent"
    }
}