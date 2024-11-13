package com.example.dagger2.di_full_project.di

import android.content.Context
import com.example.dagger2.di_full_project.data.datasource.ExampleLocalDataSource
import com.example.dagger2.di_full_project.data.datasource.ExampleLocalDataSourceImpl
import com.example.dagger2.di_full_project.data.datasource.ExampleRemoteDataSource
import com.example.dagger2.di_full_project.data.datasource.ExampleRemoteDataSourceImpl
import dagger.Module
import dagger.Provides

@Module
class DataModule(private val context:Context) {
    @Provides
    fun provideContext(): Context {
        return context
    }
    @Provides
    fun provideExampleLocalDataSource(impl: ExampleLocalDataSourceImpl): ExampleLocalDataSource{
        return impl
    }

    @Provides
    fun provideExampleRemoteDataSource(impl: ExampleRemoteDataSourceImpl): ExampleRemoteDataSource{
        return impl
    }
}