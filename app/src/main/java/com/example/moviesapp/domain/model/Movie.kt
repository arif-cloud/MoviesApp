package com.example.moviesapp.domain.model

data class Movie(
    val id: Int?,
    val backdrop_path: String?,
    val genre_ids: List<Int>?,
    val overview: String?,
    val poster_path: String?,
    val title: String?,
    val vote_average: Double?
)