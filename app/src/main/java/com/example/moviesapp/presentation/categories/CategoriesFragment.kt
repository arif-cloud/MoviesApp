package com.example.moviesapp.presentation.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.presentation.adapter.CategoryAdapter
import com.example.moviesapp.databinding.FragmentCategoriesBinding
import com.example.moviesapp.presentation.adapter.MovieAdapter
import com.example.moviesapp.presentation.sheet.MovieSheet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoriesFragment : Fragment() {
    private val categoriesViewModel : CategoriesViewModel by viewModels()
    private val categoryAdapter = CategoryAdapter()
    private val movieAdapter = MovieAdapter()
    private lateinit var binding : FragmentCategoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        if (movieAdapter.getManager() == null) movieAdapter.setManager(parentFragmentManager)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()

        binding.apply {
            if (movieSearchView.query.isEmpty()) {
                defaultData()
            }
            movieSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText : String?): Boolean {
                    if (!newText.isNullOrEmpty())
                        searchMovieData(newText)
                    else
                        defaultData()
                    return true
                }
            })
        }
    }

    private fun observeLiveData() {
        binding.apply {
            categoriesViewModel.categories.observe(viewLifecycleOwner, Observer { categories ->
                categoryAdapter.updateGenreList(categories)
            })
            categoriesViewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
                movieAdapter.updateMovieList(movies)
            })
        }
    }

    private fun searchMovieData(query : String) {
        lifecycleScope.launch {
            categoriesViewModel.getMoviesBySearch(query)
        }
        binding.apply {
            categoriesRecyclerView.adapter = movieAdapter
            categoriesRecyclerView.layoutManager = GridLayoutManager(this@CategoriesFragment.context,2)
        }
    }

    private fun defaultData() {
        binding.apply {
            categoriesRecyclerView.adapter = categoryAdapter
            categoriesRecyclerView.layoutManager = LinearLayoutManager(this@CategoriesFragment.context)
        }
    }
}