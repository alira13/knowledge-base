package com.example.broadcastreceiver

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {
    private val localBroadcastManager: LocalBroadcastManager by lazy {
        LocalBroadcastManager.getInstance(this)
    }

    private val systemOrCustomBroadcastReceiver = MyBroadcastReceiver()
    private var clickNum = 0

    // когда мы хотим делать какие-то действия у UI при получении оповещения о событии, можем прям здесь создать MyBroadcastReceiver
    private lateinit var progressBar: ProgressBar

    private val progressBarBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                // хотим как-то реагировать на прогресс загрузки данных
                LoadingService.INTENT_NAME -> {
                    val progress = intent.getIntExtra(LoadingService.INTENT_DATA, 0)
                    progressBar.progress = progress
                }
            }
        }
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
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
            addAction(MyBroadcastReceiver.ACTION_CLICKED)
        }

        // если сами хотим отправить оповещение и допустим с параметром
        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener {
            val intent = Intent(MyBroadcastReceiver.ACTION_CLICKED)
            intent.putExtra(MyBroadcastReceiver.ACTION_CLICK_NUM, clickNum++)
            sendBroadcast(intent)
        }
        // регистрируем receiver
        // динамическая регистрация receiver - в момент работы программы - наиболее частый
        // статическая регистрация в manifest c добавлением фильтров
        // - начиная с версии 26 не реагируют на большинство action
        // используются только когда нужно реагировать после перезапуска телефона
        // не нужно убивать в onDestroy
        registerReceiver(systemOrCustomBroadcastReceiver, intentFilter)
        //registerReceiver(myBroadcastReceiver, intentFilterLowBattery)


        progressBar = findViewById(R.id.pb)

        // стартуем сервис в котором будет отправляться оповещение с заданным Intent после загрузки на каждое событие
        val intent = Intent(this, LoadingService::class.java).apply {
            startService(this)
        }
        // говорим какого типа интент будем ловить
        val progressBarIntentFilter = IntentFilter(LoadingService.INTENT_NAME)
        // подписываемся
        localBroadcastManager.registerReceiver(progressBarBroadcastReceiver, progressBarIntentFilter)
    }

    // чтобы не было утечек памяти, нужно отписаться
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(systemOrCustomBroadcastReceiver)
        localBroadcastManager.unregisterReceiver(progressBarBroadcastReceiver)
    }
}