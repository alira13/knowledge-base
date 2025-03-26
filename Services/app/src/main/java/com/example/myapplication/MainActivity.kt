package com.example.myapplication

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager

class MainActivity : AppCompatActivity() {

    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btn5: Button
    private lateinit var btn6: Button

    private var page = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btn1 = findViewById(R.id.btn_service1)
        btn1.setOnClickListener {
            startService(MyService.newIntent(this))
        }

        btn2 = findViewById(R.id.btn_service2)
        btn2.setOnClickListener {
            //проверит под капотом нужную версию Android и вызовет startService если версия ниже чем 26
            ActivityCompat.startForegroundService(this, MyForegroundService.newIntent(this))
        }

        btn3 = findViewById(R.id.btn_service3)
        btn3.setOnClickListener {
            //проверит под капотом нужную версию Android и вызовет startService если версия ниже чем 26
            ActivityCompat.startForegroundService(this, MyIntentService.newIntent(this))
        }

        btn4 = findViewById(R.id.btn_service4)
        btn4.setOnClickListener {
            // компонент в котором мы указываем наш класс сервиса
            val component = ComponentName(this, MyJobService::class.java)
            // тут мы описываем условия по которым будем стартовать сервис
            val jobInfo = JobInfo.Builder(111, component)
                // только на зарядке
                .setRequiresCharging(true)
                // только с wifi
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .build()
            // получаем планировщика и запускаем с условиями
            val jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
            jobScheduler.schedule(jobInfo)
        }

        btn6 = findViewById(R.id.btn_service6)
        btn6.setOnClickListener {
            val workManager = WorkManager.getInstance(applicationContext)
            // создастся 10 workers и все они будут выполнятьтся
            //workManager.enqueue()
            // в 1 время работает 1 воркер. Передаем параметр что делать если какой-то воркер был запущен
            // и мы пытаемся запустить новый
            workManager.enqueueUniqueWork(
                MyWorker.WORKER_NAME,
                ExistingWorkPolicy.APPEND,
                MyWorker.createRequest(page++)
            )
        }
    }
}