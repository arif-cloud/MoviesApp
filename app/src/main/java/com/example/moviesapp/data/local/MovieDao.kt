package com.example.moviesapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movie : MovieEntity)

    @Query("SELECT * FROM movies")
    fun readAllData() : LiveData<List<MovieEntity>>

    @Query("SELECT EXISTS(SELECT * FROM movies WHERE title = :title)")
    fun isMovieExist(title : String): Boolean

    @Delete
    suspend fun deleteMovie(movie : MovieEntity)
}