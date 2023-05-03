package com.example.moviesapp.domain.use_cases

import android.view.View
import com.example.moviesapp.common.Constants
import com.example.moviesapp.data.mappers.toMovie
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository : MovieRepository
) {

    operator fun invoke() : Flow<ArrayList<Movie>> = flow {
        try {
            val response = repository.getMovies("popular",Constants.API_KEY)
            val popularMovieList = ArrayList(response.results.map { it.toMovie() })
            emit(popularMovieList)
        } catch (e : HttpException) {
            println(e.localizedMessage)
        }
    }
}