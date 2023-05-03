package com.example.moviesapp.presentation.sheet

import android.os.Bundle
import android.text.BoringLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.moviesapp.common.Constants
import com.example.moviesapp.data.local.MovieEntity
import com.example.moviesapp.data.mappers.toMovieEntity
import com.example.moviesapp.databinding.BottomSheetBinding
import com.example.moviesapp.domain.model.Movie
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieSheet(val movie : Movie) : BottomSheetDialogFragment() {
    private lateinit var binding : BottomSheetBinding
    private val movieViewModel : MovieViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            Glide.with(this@MovieSheet).load(Constants.BASE_IMAGE + movie.poster_path).into(detailImageView)
            detailNameTextView.text = movie.title
            rateTextView.text = movie.vote_average.toString()
            detailPreviewTextView.text = movie.overview

            val movieEntity : MovieEntity = movie.toMovieEntity()
            val isAdded = movieViewModel.isMovieExist(movieEntity.title!!)

            if (isAdded)
                addMovieButton.text = "Added"
            else
                addMovieButton.text = "Add Movie"

            addMovieButton.setOnClickListener {
                if (isAdded) {
                    movieViewModel.deleteMovie(movieEntity)
                    addMovieButton.text = "Add Movie"
                } else {
                    movieViewModel.addMovie(movieEntity)
                    addMovieButton.text = "Added"
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = BottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }
}