package com.example.dagger2.di_full_project.data.repository

import com.example.dagger2.di_full_project.data.datasource.ExampleLocalDataSource
import com.example.dagger2.di_full_project.data.datasource.ExampleRemoteDataSource
import com.example.dagger2.di_full_project.data.mapper.ExampleMapper
import com.example.dagger2.di_full_project.domain.ExampleRepository
import javax.inject.Inject

class ExampleRepositoryImpl @Inject constructor(
    private val localDataSource: ExampleLocalDataSource,
    private val remoteDataSource: ExampleRemoteDataSource,
    private val mapper: ExampleMapper
) : ExampleRepository {

    override fun method() {
        mapper.map()
        localDataSource.method()
        remoteDataSource.method()
    }
}
