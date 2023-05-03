package com.example.moviesapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val id: Int?,
    val backdrop_path: String?,
    val overview: String?,
    val poster_path: String?,
    val title: String?,
    val vote_average: Double?
)
