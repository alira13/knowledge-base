package com.example.room

import androidx.room.ColumnInfo
import androidx.room.ColumnInfo.Companion.TEXT
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class NewMovieEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo("movie_id")
    val id: Long,
    @ColumnInfo(name = "movie_name", typeAffinity = TEXT)
    val title: String,
    @ColumnInfo(name = "description", typeAffinity = TEXT)
    val description: String = "Какое-то описание"
) {

}