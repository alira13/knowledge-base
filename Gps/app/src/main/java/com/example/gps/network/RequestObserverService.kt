package com.example.gps.network

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.example.gps.GpsModel
import com.example.gps.LocationModel
import com.example.gps.MetaModel
import com.example.gps.R
import com.example.gps.RootActivity
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlin.coroutines.cancellation.CancellationException

class RequestObserverService : LifecycleService() {

    private lateinit var notificationManager: NotificationManager

    private val httpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    private val api: MetaRepositoryImpl = MetaRepositoryImpl(
        api = Api(
            httpClient
        )
    )

    override fun onCreate() {
        super.onCreate()
        Log.d("MY", "Service onStart")

        createChannel()
        startForeground(ONGOING_NOTIFICATION_ID, createNotification("Send request"))

        sendRequestLoop()
    }

    private fun createNotification(contentText: String): Notification {
        val pendingIntent: PendingIntent =
            Intent(this, RootActivity::class.java).let { notificationIntent ->
                PendingIntent.getActivity(
                    this, 0, notificationIntent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            }

        return Notification.Builder(this, CHANNEL_ID)
            .setContentTitle(getText(R.string.app_name))
            .setContentText(contentText)
            .setContentIntent(pendingIntent)
            .build()
    }

    private fun createChannel() {
        val name = CHANNEL_NAME
        val descriptionText = ""
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val mChannel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            setShowBadge(false)
        }
        mChannel.description = descriptionText
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)
    }

    private fun sendRequestLoop() {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        Log.d("MY", "sendRequestLoop")
        lifecycleScope.launch {
            while (isActive) {
                Log.d("MY", "sendRequestLoop $SEND_REQUEST_DELAY")
                sendRequest()
                delay(SEND_REQUEST_DELAY)
            }
        }
    }

    private suspend fun sendRequest() {
        val gpsData = GpsModel()
        val metadata = MetaModel()
        val data = LocationModel(
            gps = gpsData,
            meta = metadata
        )

        try {
            val response = api.sendMetaInformation(data = data)
            Log.d ( "MY","response = $response" )
           // if(response.error != null) {
           //     Log.d ( "MY","[GPS ERROR] Send location error! ${response.error}" )
          //  }
        } catch (th: Throwable) {
            if (th !is CancellationException) {
                Log.d ( "MY","response error! " )
            } else {
                Log.d ("MY","response error!" )
                throw th
            }
        }
    }

    companion object {
        private const val CHANNEL_ID = "main_channel_id"
        private const val CHANNEL_NAME = "main_channel"
        private const val ONGOING_NOTIFICATION_ID = 723
        private const val SEND_REQUEST_DELAY: Long = 1_000L

        fun start(ctx: Context) {
            Log.d("MY", "Start service")
            val intent = Intent(ctx, RequestObserverService::class.java)
            ContextCompat.startForegroundService(ctx, intent)
        }

        fun stop(ctx: Context) {
            Log.d("MY", "Stop service")
            val stopIntent = Intent(ctx, RequestObserverService::class.java)
            ctx.stopService(stopIntent)
        }
    }
}