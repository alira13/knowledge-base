package com.example.dagger2.di_full_project.data.network

import android.util.Log
import javax.inject.Inject

class ExampleApiService @Inject constructor() {

    fun method() {
        Log.d("MY_LOG", "ExampleApiService method")
    }
}
