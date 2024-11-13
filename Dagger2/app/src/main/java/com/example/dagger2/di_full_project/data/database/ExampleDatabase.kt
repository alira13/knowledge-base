package com.example.dagger2.di_full_project.data.database

import android.util.Log
import javax.inject.Inject

class ExampleDatabase @Inject constructor() {

    fun method() {
        Log.d("MY_LOG", "ExampleDatabase method")
    }
}
