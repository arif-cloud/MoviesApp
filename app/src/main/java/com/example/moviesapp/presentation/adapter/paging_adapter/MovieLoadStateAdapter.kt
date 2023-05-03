package com.example.moviesapp.presentation.adapter.paging_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.ProgressRowBinding

class MovieLoadStateAdapter : LoadStateAdapter<MovieLoadStateAdapter.LoadStateViewHolder>() {

    inner class LoadStateViewHolder(val binding : ProgressRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
            }
        }
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val view = ProgressRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LoadStateViewHolder(view)
    }
}