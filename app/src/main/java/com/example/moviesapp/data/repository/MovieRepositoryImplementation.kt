package com.example.moviesapp.data.repository

import com.example.moviesapp.data.remote.MovieApi
import com.example.moviesapp.data.remote.response.GenreResponse
import com.example.moviesapp.data.remote.response.MovieResponse
import com.example.moviesapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImplementation @Inject constructor(
    private val api : MovieApi
) : MovieRepository{
    override suspend fun getMovies(type: String, api_key: String): MovieResponse {
        return api.getMovies(type, api_key)
    }

    override suspend fun getCategories(api_key: String): GenreResponse {
        return api.getCategories(api_key)
    }

    override suspend fun getMoviesByGenre(page : Int, with_genres: Int, api_key: String): MovieResponse {
        return api.getMoviesByGenre(page,with_genres, api_key)
    }

    override suspend fun getMoviesBySearch(query: String, api_key: String): MovieResponse {
        return api.getMoviesBySearch(query, api_key)
    }
}