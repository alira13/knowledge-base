package com.example.dagger2.di_full_project.di

import androidx.lifecycle.ViewModel
import com.example.dagger2.di_full_project.presentation.AnotherExampleViewModel
import com.example.dagger2.di_full_project.presentation.ExampleViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
interface ViewModelModule {
    @IntoMap
    @StringKey("ExampleViewModel")
    @Binds
    fun bindExampleViewModel(impl: ExampleViewModel): ViewModel

    @IntoMap
    @StringKey("AnotherExampleViewModel")
    @Binds
    fun bindAnotherExampleViewModel(impl: AnotherExampleViewModel): ViewModel
}