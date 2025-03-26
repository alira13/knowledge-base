package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

open class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                // сейчас Intent создает система и кладет нужные данные в Intent тоже система
                val turnOn = intent.getBooleanExtra("state", false)
                Toast.makeText(context, "Airplane mode is turnOn = $turnOn", Toast.LENGTH_SHORT)
                    .show()
            }

            Intent.ACTION_BATTERY_LOW -> {
                Toast.makeText(context, "Low battery", Toast.LENGTH_SHORT).show()
            }

            // когда создаем свое событие и на него реагируем
            ACTION_CLICKED -> {
                val clickNum = intent.getIntExtra(ACTION_CLICK_NUM, 1)
                Toast.makeText(context, "Button clicked $clickNum", Toast.LENGTH_SHORT).show()
            }


        }
    }

    companion object {
        const val ACTION_CLICKED = "clicked"
        const val ACTION_CLICK_NUM = "clickNum"
    }
}