package com.example.myapplication

import javax.inject.Inject
import javax.inject.Singleton


class MusicRepository @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource,
)