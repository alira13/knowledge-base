package com.example.broadcastreceiver

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val myBroadcastReceiver = MyBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // добавляем фильтры чтобы реагировать на конкретные события
        val intentFilterAirplaneMode = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        val intentFilterLowBattery = IntentFilter(Intent.ACTION_BATTERY_LOW)
        // можем сначала создать intentFilter, а потом добавить несколько фильтров в него
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }

        // регистрируем receiver
        // динамическая регистрация receiver - в момент работы программы - наиболее частый
        // статическая регистрация в manifest c добавлением фильтров
        // - начиная с версии 26 не реагируют на большинство action
        // используются только когда нужно реагировать после перезапуска телефона
        // не нужно убивать в onDestroy
        registerReceiver(myBroadcastReceiver, intentFilter)
        //registerReceiver(myBroadcastReceiver, intentFilterLowBattery)
    }

    // чтобы не было утечек памяти, нужно отписаться
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myBroadcastReceiver)
    }
}