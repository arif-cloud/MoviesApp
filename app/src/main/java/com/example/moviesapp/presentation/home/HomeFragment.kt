package com.example.moviesapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moviesapp.common.Constants
import com.example.moviesapp.presentation.adapter.MovieAdapter
import com.example.moviesapp.databinding.FragmentHomeBinding
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.presentation.sheet.MovieSheet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val homeViewModel : HomeViewModel by viewModels()
    private val popularMovieAdapter = MovieAdapter()
    private val topRatedMovieAdapter = MovieAdapter()
    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        if (topRatedMovieAdapter.getManager() == null) topRatedMovieAdapter.setManager(parentFragmentManager)
        if (popularMovieAdapter.getManager() == null) popularMovieAdapter.setManager(parentFragmentManager)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popularMovieAdapter.setManager(parentFragmentManager)
        topRatedMovieAdapter.setManager(parentFragmentManager)

        observeLiveData()

        binding.apply {
            popularRecyclerView.adapter = popularMovieAdapter
            popularRecyclerView.layoutManager = LinearLayoutManager(this@HomeFragment.context,LinearLayoutManager.HORIZONTAL,false)

            topRatedRecyclerView.adapter = topRatedMovieAdapter
            topRatedRecyclerView.layoutManager = LinearLayoutManager(this@HomeFragment.context,LinearLayoutManager.HORIZONTAL,false)


        }

    }

    private fun observeLiveData() {
        binding.apply {
            homeViewModel.popularMovies.observe(viewLifecycleOwner, Observer {popularMovies ->
                popularMovieAdapter.updateMovieList(popularMovies)
                popularMovieAdapter.setSimpleRow()
            })
            homeViewModel.topRatedMovies.observe(viewLifecycleOwner, Observer {topRatedMovies ->
                topRatedMovieAdapter.updateMovieList(topRatedMovies)
                updateChooseMovie(topRatedMovies)
                topRatedMovieAdapter.setSimpleRow()
            })
        }
    }

    private fun updateChooseMovie(movieList : ArrayList<Movie>) {
        val randomNumber = (0..19).random()
        val chooseMovie = movieList[randomNumber]
        Glide.with(this@HomeFragment).load(Constants.BASE_IMAGE + chooseMovie.backdrop_path).into(binding.homeImageView)
        binding.movieNameTextView.setText(chooseMovie.title)
    }
}