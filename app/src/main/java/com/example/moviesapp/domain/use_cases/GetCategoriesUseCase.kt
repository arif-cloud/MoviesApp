package com.example.moviesapp.domain.use_cases

import com.example.moviesapp.common.Constants
import com.example.moviesapp.domain.model.Genre
import com.example.moviesapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke() : Flow<ArrayList<Genre>> = flow {
        try {
            val genreList = repository.getCategories(Constants.API_KEY).genres
            emit(genreList)
        } catch (e : HttpException) {
            println(e.localizedMessage)
        }
    }
}