package com.example.dagger2.di_full_project.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.dagger2.di_full_project.domain.ExampleUseCase
import javax.inject.Inject

class AnotherExampleViewModel @Inject constructor(
    private val useCase: ExampleUseCase
) : ViewModel() {

    fun method() {
        useCase.invoke()
        Log.d("MY_MY_LOG", "$this")
    }
}
