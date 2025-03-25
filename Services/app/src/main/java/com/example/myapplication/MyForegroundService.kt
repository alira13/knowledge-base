package com.example.myapplication

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// не забудь добавить в Manifest иначе не заработает
// потребует доп permissons в Manifest
class MyForegroundService : Service() {
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onBind(p0: Intent?): IBinder? {
        showLog("onBind")
        TODO("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()
        showLog("onCreate")
        startForeground(1, createNotification())
    }

    //работа сервиса происходит в Main потоке, поэтому надо запускать это в корутине,
    // чтобы была возможность приостанавливать поток и отдавать UI ресурсы чтобы он не блокировался
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showLog("onStartCommand")
        scope.launch {
            //здесь сервис работает 3 сек и OnDestroy не вызывается, уведомление нельзя удалить после уничтожения сервиса
            for (i in 0..3) {
                showLog(i.toString())
                delay(1000L)
            }
            // для остановки сервиса в таком случае нужно вручную его остановить
            stopSelf()
        }
        // вот тут есть разные варианты поведения после уничтожения сервиса
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        showLog("onDestroy")
        scope.cancel()
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
            return Intent(context, MyForegroundService::class.java)
        }
    }
}