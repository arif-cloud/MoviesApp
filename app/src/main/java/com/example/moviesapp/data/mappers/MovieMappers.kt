package com.example.moviesapp.data.mappers

import com.example.moviesapp.data.local.MovieEntity
import com.example.moviesapp.data.remote.dto.MovieDto
import com.example.moviesapp.domain.model.Movie

fun MovieDto.toMovie() : Movie {
    return Movie(
        id = id,
        title = title,
        genre_ids = genre_ids,
        backdrop_path = backdrop_path,
        poster_path = poster_path,
        overview = overview,
        vote_average = vote_average
    )
}

fun Movie.toMovieEntity() : MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        backdrop_path = backdrop_path,
        poster_path = poster_path,
        overview = overview,
        vote_average = vote_average
    )
}

fun MovieEntity.toMovie() : Movie {
    return Movie(
        id = id,
        title = title,
        backdrop_path = backdrop_path,
        poster_path = poster_path,
        overview = overview,
        vote_average = vote_average,
        genre_ids = null
    )
}