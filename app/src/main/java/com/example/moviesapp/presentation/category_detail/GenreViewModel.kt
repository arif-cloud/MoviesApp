package com.example.moviesapp.presentation.category_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.repository.MovieRepository
import com.example.moviesapp.paging.MoviePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    fun getMoviesByGenre(withGenres : Int) : Flow<PagingData<Movie>> {
        val movieList = Pager(PagingConfig(pageSize = 1)) {
            MoviePagingSource(repository, withGenres)
        }.flow.cachedIn(viewModelScope)
        return movieList
    }
}