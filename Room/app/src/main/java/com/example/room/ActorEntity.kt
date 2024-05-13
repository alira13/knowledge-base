package com.example.room

import androidx.room.ColumnInfo
import androidx.room.ColumnInfo.Companion.TEXT
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "actor_table")
data class ActorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "actor_name", typeAffinity = TEXT)
    val name: String
) {
}