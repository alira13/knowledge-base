package com.example.dagger2.di_full_project.di

import com.example.dagger2.di_full_project.data.datasource.ExampleLocalDataSource
import com.example.dagger2.di_full_project.data.datasource.ExampleLocalDataSourceImpl
import com.example.dagger2.di_full_project.data.datasource.ExampleRemoteDataSource
import com.example.dagger2.di_full_project.data.datasource.ExampleRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @AppScope
    @Binds
    fun bindsExampleLocalDataSource(impl: ExampleLocalDataSourceImpl): ExampleLocalDataSource

    @AppScope
    @Binds
    fun bindRemoteDataSource(impl: ExampleRemoteDataSourceImpl): ExampleRemoteDataSource
}