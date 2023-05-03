package com.example.moviesapp.presentation.watch_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.data.mappers.toMovie
import com.example.moviesapp.presentation.adapter.MovieAdapter
import com.example.moviesapp.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {
    private val watchListViewModel: WatchListViewModel by viewModels()
    private val watchListMovieAdapter = MovieAdapter()
    private lateinit var binding : FragmentListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)
        if (watchListMovieAdapter.getManager() == null) watchListMovieAdapter.setManager(parentFragmentManager)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        watchListViewModel.getWatchListData().observe(viewLifecycleOwner, Observer { result ->
            result?.let { movieEntityList ->
                watchListMovieAdapter.updateMovieList(ArrayList(movieEntityList.map { it.toMovie() }))
            }
        })
        binding.apply {
            watchListRecyclerView.adapter = watchListMovieAdapter
            watchListRecyclerView.layoutManager = GridLayoutManager(this@ListFragment.context,2)
        }

    }
}