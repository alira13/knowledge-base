package com.example.myapplication

import android.app.IntentService
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat

// не забудь добавить в Manifest иначе не заработает
// потребует доп permissons в Manifest
// Решщает проблемы класса Service
//1. выполняется на главном потоке и нам нужно самим запускать в корутине
//2. не останавливается сам - следить за остановкой
//3. если хотим чтобы работал 1 сервис, то надо реализовать самостоятельно,
// потому что по умолчанию каждый раз по клику кнопки будет запускаться сервис новый
class MyIntentService : IntentService("MyIntentService") {
    init {
        // не в onHandleIntent определяется поведение после уничтожения а через вот эти методы
        setIntentRedelivery(false)
    }

    // сразу выполняется в главном потоке но не блокирует его
    override fun onHandleIntent(p0: Intent?) {
        showLog("onHandleIntent")
        for (i in 0..3) {
            showLog(i.toString())
            Thread.sleep(1000L)
        }
        // сам остановится
        // stopSelf()
        // вызовы сервиса выполняются последовательно. Один закончил работать - другой начал
    }

    override fun onCreate() {
        super.onCreate()
        showLog("onCreate")
        startForeground(1, createNotification())
    }

    override fun onDestroy() {
        super.onDestroy()
        showLog("onDestroy")
    }

    private fun showLog(str: String) {
        Log.d("MY_SERVICE", "MyForegroundService: $str")
    }

    // c 26 версии нужно показывать уведомление при запуске сервиса
    private fun createNotification(): Notification {
        // c 26 версии нужно показывать уведомление в определенном канале
        val channelId = "my_channel"
        val channelName = "Foreground Service Channel"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW)
            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("Foreground Service")
            .setContentText("Сервис работает...")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MyIntentService::class.java)
        }
    }
}