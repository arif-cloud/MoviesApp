package com.example.moviesapp.data.remote

import com.example.moviesapp.data.remote.response.GenreResponse
import com.example.moviesapp.data.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/{type}")
    suspend fun getMovies(@Path("type") type : String,@Query("api_key") api_key : String) : MovieResponse

    @GET("genre/movie/list")
    suspend fun getCategories(@Query("api_key") api_key : String) : GenreResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenre(@Query("page") page : Int, @Query("with_genres") with_genres : Int, @Query("api_key") api_key : String) : MovieResponse

    @GET("search/movie")
    suspend fun getMoviesBySearch(@Query("query") query : String, @Query("api_key") api_key : String) : MovieResponse

}