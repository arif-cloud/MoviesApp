package com.example.moviesapp.data.remote.response

import com.example.moviesapp.data.remote.dto.MovieDto

data class MovieResponse(
    val page : Int,
    val results : ArrayList<MovieDto>
)
