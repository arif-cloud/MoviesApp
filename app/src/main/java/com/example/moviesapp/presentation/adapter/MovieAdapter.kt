package com.example.moviesapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.common.Constants
import com.example.moviesapp.databinding.GridMovieItemRowBinding
import com.example.moviesapp.databinding.VerticalMovieItemRowBinding
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.presentation.adapter.view_holder.MovieViewHolder
import com.example.moviesapp.presentation.sheet.MovieSheet
import java.lang.IllegalArgumentException

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {
    private val movieList : ArrayList<Movie> = arrayListOf()
    private var detailedRow : Boolean = true
    private var manager : FragmentManager? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return when(viewType) {
            R.layout.grid_movie_item_row -> MovieViewHolder.GridViewHolder(
                GridMovieItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            )
            R.layout.vertical_movie_item_row -> MovieViewHolder.VerticalViewHolder(
                VerticalMovieItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            )
            else -> throw IllegalArgumentException("Invalid ViewType Provider")
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        when(holder){
            is MovieViewHolder.GridViewHolder -> holder.bind(movie)
            is MovieViewHolder.VerticalViewHolder -> holder.bind(movie)
        }
        holder.itemView.setOnClickListener {
            manager?.let {
                MovieSheet(movie).show(it,"movie_tag")
            }
        }
    }

    fun updateMovieList(newMovieList : ArrayList<Movie>) {
        movieList.clear()
        movieList.addAll(newMovieList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (detailedRow)
            R.layout.vertical_movie_item_row
        else
            R.layout.grid_movie_item_row
    }

    fun setSimpleRow() {
        detailedRow = false
    }

    fun setManager(fragmentManager: FragmentManager) {
        manager = fragmentManager
    }

    fun getManager() : FragmentManager? {
        return manager
    }
}