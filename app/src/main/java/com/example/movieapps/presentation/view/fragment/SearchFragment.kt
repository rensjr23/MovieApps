package com.example.movieapps.presentation.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.movieapps.data.dto.Movie
import com.example.movieapps.data.dto.SearchItem
import com.example.movieapps.databinding.FragmentSearchBinding
import com.example.movieapps.presentation.adapter.MovieAdapter
import com.example.movieapps.presentation.adapter.SearchMovieAdapter
import com.example.movieapps.presentation.base.BaseFragment
import com.example.movieapps.presentation.view.viewmodel.MovieViewModel
import com.example.movieapps.presentation.view.viewmodel.SearchMovieViewModel
import com.example.movieapps.utils.DataReloadable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(), DataReloadable {
    private val viewModel: SearchMovieViewModel by viewModels()
    private var _searchMovieAdapter: SearchMovieAdapter? = null
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun setupView() {
        binding.etSearch.addTextChangedListener { text ->
            val query = text.toString().trim().toLowerCase()

            if (query.length >= 3) {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.setSearchMovie(query)
                }
            }
        }
        setupError()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.searchMovieData.observe(viewLifecycleOwner) {
            setupViewSearchMovie(it)
        }
    }
    private fun setupError(){
        viewModel.Error.observe(viewLifecycleOwner){
            if (it){
                binding.tvReload.visibility = View.VISIBLE
                binding.rvMovieResult.visibility = View.GONE
            }else{
                binding.tvReload.visibility = View.GONE
                binding.rvMovieResult.visibility = View.VISIBLE
            }
        }
    }

    private fun setupViewSearchMovie(data: List<SearchItem>) {
        _searchMovieAdapter = SearchMovieAdapter(data) { id ->
            val bundle = bundleOf("movieId" to id)
            val action =
                SearchFragmentDirections.actionSearchFragmentToMovieDetailsFragment().actionId
            findNavController().navigate(action, bundle)
        }
        binding.rvMovieResult.adapter = _searchMovieAdapter
    }

    override fun reloadData() {
        setupView()
    }

}