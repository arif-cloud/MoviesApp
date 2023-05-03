package com.example.moviesapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.GenreItemRowBinding
import com.example.moviesapp.domain.model.Genre
import com.example.moviesapp.presentation.categories.CategoriesFragmentDirections

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.GenreViewHolder>(){
    private val genreList : ArrayList<Genre> = arrayListOf()

    inner class GenreViewHolder(val binding : GenreItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Genre) {
            binding.categoryTextView.setText(item.name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = GenreItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GenreViewHolder(view)
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genreList[position])
        holder.itemView.setOnClickListener {
            val action = CategoriesFragmentDirections.actionCategoriesFragmentToGenreFragment(genreList[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateGenreList(newMovieList : ArrayList<Genre>) {
        genreList.clear()
        genreList.addAll(newMovieList)
        notifyDataSetChanged()
    }
}