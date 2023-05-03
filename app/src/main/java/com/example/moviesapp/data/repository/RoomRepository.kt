package com.example.moviesapp.data.repository

import androidx.lifecycle.LiveData
import com.example.moviesapp.data.local.MovieDao
import com.example.moviesapp.data.local.MovieEntity
import javax.inject.Inject


class RoomRepository @Inject constructor(
    private val movieDao : MovieDao
) {

    val readAllData : LiveData<List<MovieEntity>> = movieDao.readAllData()

    suspend fun addMovie(movie : MovieEntity) {
        movieDao.addMovie(movie)
    }

    suspend fun deleteMovie(movie: MovieEntity) {
        movieDao.deleteMovie(movie)
    }

    fun isMovieExist(title : String) : Boolean {
        return movieDao.isMovieExist(title)
    }
}