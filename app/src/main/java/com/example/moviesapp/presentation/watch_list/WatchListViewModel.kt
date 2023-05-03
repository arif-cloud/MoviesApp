package com.example.moviesapp.presentation.watch_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.data.local.MovieEntity
import com.example.moviesapp.domain.use_cases.GetWatchListDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(
    private val getWatchListDataUseCase : GetWatchListDataUseCase
) : ViewModel() {

    fun getWatchListData() : LiveData<List<MovieEntity>> {
        return getWatchListDataUseCase()
    }
}