package com.example.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface MovieDao {
    @Insert(entity = MovieEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieEntity)

    @Insert(entity = MovieEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(entity = MovieEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun updateMovie(movie: MovieEntity)

    @Delete(entity = MovieEntity::class)
    fun deleteMovieEntity(movie: MovieEntity)

    @Insert(entity = ActorEntity::class)
    fun insertActor(actorEntity: ActorEntity)

    @Transaction
    fun insertMovieAndActor(movie: MovieEntity, actor: ActorEntity){
        insertMovie(movie)
        insertActor(actor)
    }

    @Query("SELECT * FROM movie_table")
    fun getMovies():List<MovieEntity>

    @Query("SELECT * FROM movie_table WHERE movie_id=:movieId")
    fun getMoviesById(movieId:Long):List<MovieEntity>

    @Query("SELECT * FROM movie_table WHERE movie_name LIKE :movieName")
    fun searchMoviesByName(movieName:String):List<MovieEntity>
}