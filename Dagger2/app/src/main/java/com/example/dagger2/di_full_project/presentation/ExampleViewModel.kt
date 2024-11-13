package com.example.dagger2.di_full_project.presentation

import com.example.dagger2.di_full_project.domain.ExampleUseCase
import javax.inject.Inject

class ExampleViewModel @Inject constructor(
    private val useCase: ExampleUseCase
) {

    fun method() {
        useCase.invoke()
    }
}
