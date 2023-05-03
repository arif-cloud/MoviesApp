package com.example.moviesapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesapp.common.Constants
import com.example.moviesapp.data.mappers.toMovie
import com.example.moviesapp.data.remote.response.MovieResponse
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.repository.MovieRepository
import retrofit2.HttpException

class MoviePagingSource(val repository : MovieRepository, private val withGenres : Int) :  PagingSource<Int, Movie>(){
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: 1
            val response = repository.getMoviesByGenre(currentPage, withGenres, Constants.API_KEY)
            val data = response.results.map { it.toMovie() }
            val responseData = mutableListOf<Movie>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )

        } catch (e : Exception) {
            LoadResult.Error(e)
        } catch (httpE : HttpException) {
            LoadResult.Error(httpE)
        }
    }
}