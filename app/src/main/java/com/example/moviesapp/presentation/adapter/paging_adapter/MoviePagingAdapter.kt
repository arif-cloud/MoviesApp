package com.example.moviesapp.presentation.adapter.paging_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.moviesapp.databinding.VerticalMovieItemRowBinding
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.presentation.adapter.view_holder.MovieViewHolder
import com.example.moviesapp.presentation.sheet.MovieSheet
import javax.inject.Inject

class MoviePagingAdapter @Inject constructor() : PagingDataAdapter<Movie, MovieViewHolder.VerticalViewHolder>(differCallback) {
    private var manager : FragmentManager? = null
    override fun onBindViewHolder(holder: MovieViewHolder.VerticalViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
        holder.itemView.setOnClickListener {
            manager?.let {
                MovieSheet(getItem(position)!!).show(it,"movie_tag")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder.VerticalViewHolder {
        return MovieViewHolder.VerticalViewHolder(VerticalMovieItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun setManager(fragmentManager: FragmentManager) {
        manager = fragmentManager
    }

    fun getManager() : FragmentManager? {
        return manager
    }
}