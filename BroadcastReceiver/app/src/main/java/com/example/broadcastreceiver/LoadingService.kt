package com.example.broadcastreceiver

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlin.concurrent.thread

class LoadingService : Service() {
    private val localBroadcastManager: LocalBroadcastManager by lazy {
        LocalBroadcastManager.getInstance(this)
    }

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
                // вот это сообщение получат все приложения, которые знают имя, если не хотим то нужно использовать localBroadcatsManager
                localBroadcastManager.sendBroadcast(myIntent)
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    companion object {
        const val INTENT_NAME = "loaded"
        const val INTENT_DATA = "percent"
    }
}