package com.example.moviesapp.presentation.categories

import androidx.lifecycle.*
import com.example.moviesapp.domain.model.Genre
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.use_cases.GetCategoriesUseCase
import com.example.moviesapp.domain.use_cases.GetMoviesBySearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getMoviesBySearchUseCase: GetMoviesBySearchUseCase
) : ViewModel() {

    private val _categories = MutableLiveData<ArrayList<Genre>>()
    val categories : LiveData<ArrayList<Genre>> get() = _categories

    private val _movies = MutableLiveData<ArrayList<Movie>>()
    val movies : LiveData<ArrayList<Movie>> get() = _movies

    init {
        getGenresInfo()
    }

    private fun getGenresInfo() {
        getCategoriesUseCase().onEach {
            _categories.postValue(it)
        }.launchIn(viewModelScope)
    }

    fun getMoviesBySearch(query: String) {
        getMoviesBySearchUseCase(query).onEach {
            _movies.postValue(ArrayList(it))
        }.launchIn(viewModelScope)
    }
}