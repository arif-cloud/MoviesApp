package com.example.moviesapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.common.Constants
import com.example.moviesapp.data.mappers.toMovie
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.repository.MovieRepository
import com.example.moviesapp.domain.use_cases.GetPopularMoviesUseCase
import com.example.moviesapp.domain.use_cases.GetTopRatedMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMovieUseCase: GetTopRatedMovieUseCase
) : ViewModel() {

    private val _popularMovies = MutableLiveData<ArrayList<Movie>>()
    val popularMovies : LiveData<ArrayList<Movie>> get() = _popularMovies

    private val _topRatedMovies = MutableLiveData<ArrayList<Movie>>()
    val topRatedMovies : LiveData<ArrayList<Movie>> get() = _topRatedMovies

    init {
        getHomeMoviesInfo()
    }

    private fun getHomeMoviesInfo() {
        getPopularMoviesUseCase().onEach {
            _popularMovies.postValue(it)
        }.launchIn(viewModelScope)
        getTopRatedMovieUseCase().onEach {
            _topRatedMovies.postValue(it)
        }.launchIn(viewModelScope)
    }
}