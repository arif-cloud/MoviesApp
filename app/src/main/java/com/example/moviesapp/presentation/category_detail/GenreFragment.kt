package com.example.moviesapp.presentation.category_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.databinding.FragmentGenreBinding
import com.example.moviesapp.presentation.adapter.paging_adapter.MovieLoadStateAdapter
import com.example.moviesapp.presentation.adapter.paging_adapter.MoviePagingAdapter
import com.example.moviesapp.presentation.sheet.MovieSheet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GenreFragment : Fragment() {
    private val genreViewModel : GenreViewModel by viewModels()
    private val genreMovieAdapter = MoviePagingAdapter()
    private lateinit var binding : FragmentGenreBinding

    private val args by navArgs<GenreFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View {
        // Inflate the layout for this fragment
        binding = FragmentGenreBinding.inflate(inflater, container, false)
        if (genreMovieAdapter.getManager() == null) genreMovieAdapter.setManager(parentFragmentManager)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            genreViewModel.getMoviesByGenre(args.withGenre.id).collect{
                genreMovieAdapter.submitData(it)
            }
        }
        binding.apply {
            genreTitleTextView.text = args.withGenre.name
            genreRecyclerView.adapter = genreMovieAdapter.withLoadStateFooter(
                footer = MovieLoadStateAdapter()
            )
            genreRecyclerView.layoutManager = GridLayoutManager(this@GenreFragment.context,2)

            backButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}