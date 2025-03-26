package com.example.myapplication

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf


class MyWorker(context: Context, private val workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    // выполняется в main потоке и не блокирует его. Не нужно самим беспокоиться и стартовать корутину
    override fun doWork(): Result {
        showLog("doWork")
        // раличные параметры передаются через WorkerParameters.inputData
        val page = workerParams.inputData.getInt(PAGE_KEY, 0)
        for (i in 0..3) {
            showLog(i.toString())
            Thread.sleep(1000L)
        }

        return Result.success()
        // если исключение или что-то пошло не так Result.success() или Result.retry
    }

    private fun showLog(str: String) {
        Log.d("MY_SERVICE", "MyWorker: $str")
    }

    companion object {
        const val PAGE_KEY = "page"
        const val WORKER_NAME = "My worker"

        fun createRequest(page: Int): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<MyWorker>()
                // передаем данные в сервис
                .setInputData(workDataOf(PAGE_KEY to page))
                // выставляем ограничения
                .setConstraints(makeConstraints())
                .build()
        }

        private fun makeConstraints(): Constraints {
            return Constraints.Builder().build()
        }
    }
}