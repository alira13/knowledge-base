package com.example.room

import android.app.Application
import android.util.Log
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        //  allowMainThreadQueries() позволяет работать в MAIN-потоке, но это только для отладки!
        val database = Room
            .databaseBuilder(this, AppDatabase::class.java, "movies_database.db")
            .allowMainThreadQueries()
            .build()
        database.getMovieDao().insertMovie(MovieEntity(1, "Гарри Поттер"))
        val movies = database.getMovieDao().getMovies()
        Log.d("MY_LOG", movies.toString())

        val databaseAfterMigration = Room
            .databaseBuilder(this, AppDatabase::class.java, "movies_database.db")
            .allowMainThreadQueries()
            .addMigrations(MIGRATION_1_2)
            .build()
        val newMovies = databaseAfterMigration.getMovieDao().getMovies()
        Log.d("MY_LOG", newMovies.toString())
    }

    object MIGRATION_1_2 : Migration(1, 2) {
        //TODO Не работает
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE movie_table ADD COLUMN description TEXT DEFAULT 0 NOT NULL")
        }
    }
}