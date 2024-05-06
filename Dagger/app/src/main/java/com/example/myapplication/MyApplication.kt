package com.example.myapplication

import android.app.Application
import dagger.Component

@Component
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}

class MyApplication: Application() {
    val appComponent = DaggerApplicationComponent.create()
}