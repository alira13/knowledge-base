package com.example.myapplication

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// не забудь добавить в Manifest иначе не заработает
// Создание  - наследуемся от JobService
class MyJobService : JobService() {
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        showLog("onCreate")
    }

    // также на главном потоке выполняется
    // выызвается при старте сервиса, в нем выполняется вся работа
    override fun onStartJob(p0: JobParameters?): Boolean {
        showLog("onStartJob")
        //createNotification()
        scope.launch {
            for (i in 0..100) {
                showLog(i.toString())
                delay(1000L)
            }
            // если return true то вручную останавливаем
            // если после НОРМАЛЬНОГО завершения нужно перезапустить сервис то jobFinished(true)
            jobFinished(p0, true)
        }
        //СЕРВИС ЕЩЕ ВЫПОЛНЯЕТСЯ?
        // ДА(true) - потому что мы выполняем асинхронные опреации и они не завершаются сами,
        // мы завершим их когда сами решим. Кароче требуется ли остановить принудительно или нет(сама остановится)
        // НЕТ - когда мы выполняем последовательные операции синхронно и они завершаются сами
        return true
    }

    // выполняется когда  СИСТЕМА убила наш сервис(не мы сами)
    // если после СИСТЕМНОГО убиения сервиса нужно перезапустить сервис то return true
    override fun onStopJob(p0: JobParameters?): Boolean {
        showLog("onStopJob")
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        showLog("onDestroy")
        scope.cancel()
    }

    private fun showLog(str: String) {
        Log.d("MY_SERVICE", "MyService: $str")
    }
}