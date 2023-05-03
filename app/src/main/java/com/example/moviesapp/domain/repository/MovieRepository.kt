package com.example.moviesapp.domain.repository

import com.example.moviesapp.data.remote.response.GenreResponse
import com.example.moviesapp.data.remote.response.MovieResponse

interface MovieRepository {

    suspend fun getMovies(type : String, api_key : String) : MovieResponse

    suspend fun getCategories(api_key : String) : GenreResponse

    suspend fun getMoviesByGenre(page : Int, with_genres : Int, api_key : String) : MovieResponse

    suspend fun getMoviesBySearch(query : String, api_key : String) : MovieResponse
}