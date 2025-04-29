package com.example.gps.location

import android.Manifest
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.example.gps.R
import com.example.gps.RootActivity
import com.example.gps.network.MetaRepositoryImpl
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import io.ktor.client.HttpClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class LocationObserverService : LifecycleService() {
    private var currentLocation: Location? = null
    private lateinit var notificationManager: NotificationManager

    private val fusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(
            this
        )
    }

    /* LocationRequest - Requirements for the location updates, i.e., how often you should receive
        updates, the priority, etc. */
    private var locationRequest: LocationRequest? = null

    // LocationCallback - Called when FusedLocationProviderClient has a new Location.
    private var locationCallback: LocationCallback? = null

    override fun onCreate() {
        super.onCreate()
        Log.d("MY", "Service onStart")

        createChannel()
        startForeground(ONGOING_NOTIFICATION_ID, createNotification("Tracking GPS"))

        updateLocationLoop()
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

    private fun updateLocationLoop() {
        lifecycleScope.launch {
            while (isActive) {
                val isGranted = isLocationPermissionsGranted()
                Log.d("MY", "[GPS isLocationPermissionsGranted: $isGranted]")

                /*
                when {
                    isGranted && locationCallback == null -> {
                        Log.d("MY", "[GPS start updating: permissions granted]")
                        startRequestingLocation()
                    }

                    !isGranted && locationCallback != null -> {
                        Log.d("MY", "[GPS stop updating: permissions revoked]")
                        stopRequestingLocation()
                    }
                }
*/
                if(isGranted)
                    startRequestingLocation()
                else
                    stopRequestingLocation()
                delay(UPDATE_LOCATION_LOOP_DELAY)
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun startRequestingLocation() {
        Log.d("MY", "startRequestingLocation")
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Создание объекта LocationRequest с использованием Builder
        locationRequest = LocationRequest.Builder(Priority.PRIORITY_BALANCED_POWER_ACCURACY, INTERVAL)
            .setMinUpdateIntervalMillis(FASTEST_INTERVAL) // Самый быстрый интервал (5 секунд)
            .setWaitForAccurateLocation(false) // Не ждать более точного местоположения
            .build()

      /*  locationRequest = LocationRequest.create().apply {
            // Sets the desired interval for active location updates. This interval is inexact. You
            // may not receive updates at all if no location sources are available, or you may
            // receive them less frequently than requested. You may also receive updates more
            // frequently than requested if other applications are requesting location at a more
            // frequent interval.
            //
            // IMPORTANT NOTE: Apps running on Android 8.0 and higher devices (regardless of
            // targetSdkVersion) may receive updates less frequently than this interval when the app
            // is no longer in the foreground.
            interval = INTERVAL
//            interval = TimeUnit.SECONDS.toMillis(60)

            // Sets the fastest rate for active location updates. This interval is exact, and your
            // application will never receive updates more frequently than this value.
            fastestInterval = FASTEST_INTERVAL
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

       */

        // Initialize the LocationCallback
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                // Normally, you want to save a new location to a database. We are simplifying
                // things a bit and just saving it as a local variable, as we only need it again
                // if a Notification is created (when the user navigates away from app).

                currentLocation = locationResult.lastLocation
                Log.d("MY", "currentLocation = $currentLocation")
            }
        }

        val looper = Looper.myLooper()!!
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest!!,
            locationCallback!!,
            looper
        )
    }

    private fun stopRequestingLocation() {
        locationCallback?.let {
            fusedLocationProviderClient.removeLocationUpdates(it)
        }
        locationCallback = null
    }

    private fun isLocationPermissionsGranted(): Boolean {
        return !(ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED)
    }

    companion object {
        private const val CHANNEL_ID = "main_channel_id"
        private const val CHANNEL_NAME = "main_channel"
        private const val ONGOING_NOTIFICATION_ID = 723
        private const val INTERVAL: Long = 3_000L
        private const val FASTEST_INTERVAL: Long = 3_000L
        private const val UPDATE_LOCATION_LOOP_DELAY: Long = 3_000L

        fun start(ctx: Context) {
            Log.d("MY", "Start service")
            val intent = Intent(ctx, LocationObserverService::class.java)
            ContextCompat.startForegroundService(ctx, intent)
        }

        fun stop(ctx: Context) {
            Log.d("MY", "Stop service")
            val stopIntent = Intent(ctx, LocationObserverService::class.java)
            ctx.stopService(stopIntent)
        }
    }

    override fun onDestroy() {
        stopRequestingLocation()
        super.onDestroy()
    }
}