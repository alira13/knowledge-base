package com.example.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [MovieEntity::class, ActorEntity::class])
abstract class AppDatabase: RoomDatabase(){
    abstract fun getMovieDao():MovieDao

}