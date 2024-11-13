package com.example.dagger2.di_full_project.data.network

import android.content.Context
import android.util.Log
import com.example.dagger2.R
import javax.inject.Inject

class ExampleApiService @Inject constructor(
    private val context: Context,
    private val logTagName: String
) {

    fun method() {
        Log.d(
            logTagName,
            "ExampleApiService method ${context.getString(R.string.app_name)}"
        )
    }
}
