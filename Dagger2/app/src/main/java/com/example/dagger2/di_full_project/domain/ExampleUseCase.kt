package com.example.dagger2.di_full_project.domain

class ExampleUseCase(
    private val repository: ExampleRepository
) {

    operator fun invoke() {
        repository.method()
    }
}
