package com.example.moviesapp.domain.use_cases

import androidx.lifecycle.LiveData
import com.example.moviesapp.data.local.MovieEntity
import com.example.moviesapp.data.repository.RoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWatchListDataUseCase @Inject constructor(
    private val repository : RoomRepository
) {

    operator fun invoke() : LiveData<List<MovieEntity>> {
        return repository.readAllData
    }
}