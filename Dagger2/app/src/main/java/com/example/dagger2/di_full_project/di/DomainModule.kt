package com.example.dagger2.di_full_project.di

import com.example.dagger2.di_full_project.data.repository.ExampleRepositoryImpl
import com.example.dagger2.di_full_project.domain.ExampleRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {
    @Binds
    fun bindExampleRepository(impl: ExampleRepositoryImpl): ExampleRepository
}