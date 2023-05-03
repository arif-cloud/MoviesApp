package com.example.moviesapp.domain.use_cases

import android.widget.Toast
import com.example.moviesapp.common.Constants
import com.example.moviesapp.data.mappers.toMovie
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetMoviesBySearchUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(query : String) : Flow<List<Movie>> = flow {
        try {
            val response = repository.getMoviesBySearch(query, Constants.API_KEY)
            val movieList = response.results.map { it.toMovie() }
            emit(movieList)
        } catch (e : HttpException) {
            println(e.localizedMessage)
        }
    }
}