package com.example.dagger2.di_full_project.app

import android.app.Application
import com.example.dagger2.di_full_project.di.DaggerAppComponent

class ExampleApp: Application() {
    // use custom Factory
    val component by lazy {
        DaggerAppComponent.factory()
            .create(this, "MY_MY_LOG")
    }
}