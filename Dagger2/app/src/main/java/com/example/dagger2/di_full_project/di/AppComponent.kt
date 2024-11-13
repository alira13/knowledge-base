package com.example.dagger2.di_full_project.di

import com.example.dagger2.di_full_project.presentation.MainActivity
import dagger.Component

@Component(modules = [DataModule::class, DomainModule::class, AppModule::class])
interface AppComponent {
    fun inject(activity:MainActivity)
}