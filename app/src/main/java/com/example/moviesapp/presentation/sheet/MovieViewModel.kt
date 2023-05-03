package com.example.moviesapp.presentation.sheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.local.MovieEntity
import com.example.moviesapp.data.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository : RoomRepository
) : ViewModel(){

    fun addMovie(movieEntity: MovieEntity) {
        viewModelScope.launch {
            repository.addMovie(movieEntity)
        }
    }

    fun deleteMovie(movieEntity: MovieEntity) {
        viewModelScope.launch {
            repository.deleteMovie(movieEntity)
        }
    }

    fun isMovieExist(title : String) : Boolean {
        return repository.isMovieExist(title)
    }
}