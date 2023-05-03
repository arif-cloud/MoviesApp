package com.example.moviesapp.presentation.adapter.view_holder

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.moviesapp.common.Constants
import com.example.moviesapp.databinding.GridMovieItemRowBinding
import com.example.moviesapp.databinding.VerticalMovieItemRowBinding
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.presentation.sheet.MovieSheet

sealed class MovieViewHolder(binding : ViewBinding) : RecyclerView.ViewHolder(binding.root){

    class GridViewHolder(private val binding: GridMovieItemRowBinding) : MovieViewHolder(binding) {
        fun bind(movie : Movie) {
            binding.apply {
                Glide.with(itemView.context).load(Constants.BASE_IMAGE + movie.poster_path).into(movieImageView)
            }
        }
    }

    class VerticalViewHolder(private val binding : VerticalMovieItemRowBinding) : MovieViewHolder(binding) {
        fun bind(movie: Movie) {
            binding.apply {
                Glide.with(itemView.context).load(Constants.BASE_IMAGE + movie.poster_path).into(movieImageView)
                movieNameTextView.text = movie.title
            }
        }
    }
}