package com.example.movieapps.presentation.view.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieDrawable
import com.example.movieapps.R
import com.example.movieapps.databinding.FragmentMovieBinding
import com.example.movieapps.presentation.adapter.MovieAdapter
import com.example.movieapps.presentation.base.BaseFragment
import com.example.movieapps.presentation.view.viewmodel.MovieViewModel
import com.example.movieapps.utils.DataReloadable
import com.example.movieapps.utils.setGone
import com.example.movieapps.utils.setVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.log

@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMovieBinding>(), DataReloadable {
    private val viewModel: MovieViewModel by viewModels()
    private var _movieAdapter: MovieAdapter? = null
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMovieBinding {
        return FragmentMovieBinding.inflate(inflater, container, false)
    }

    override fun setupView() {
        setupSuccess()
        setupError()
        observeViewModel()
        setupViewMovie()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movieData.collectLatest {
                    if (it != null) {
                        _movieAdapter?.submitData(it)
                    }
                }
            }
        }
    }

    private fun setupSuccess() {
        viewModel.apply {
            getPassedGenreId()?.let {
                setMovieData(
                    it
                )
            }
        }
    }

    private fun setupError() {
        viewModel.Error.observe(viewLifecycleOwner) {
            if (it) {
                binding.tvReload.setVisible()
                binding.tvTitlePage.setGone()
                binding.componentMovie.gridMovie.setGone()
            } else {
                binding.tvReload.setGone()
                binding.tvTitlePage.setVisible()
                binding.componentMovie.gridMovie.setVisible()
            }
        }
    }

    private fun getPassedGenreId(): Int? {
        return arguments?.getInt("id")
    }

    private fun setupViewMovie() {
        _movieAdapter = MovieAdapter {id->
            val bundle = bundleOf("movieId" to id)
            val action =
                MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment().actionId
            findNavController().navigate(action, bundle)
        }
        binding.componentMovie.gridMovie.adapter = _movieAdapter
        val title = arguments?.getString("title")
        binding.tvTitlePage.text = "Genre ${title}"
    }

    private fun setLoading(isLoading: Boolean) {
        binding.loadingSplash.apply {
            visibility = if (isLoading) View.VISIBLE else View.GONE
            setAnimation(R.raw.lo_loadingnew)
            repeatCount = LottieDrawable.INFINITE
            playAnimation()
        }
    }

    override fun reloadData() {
        setupView()
    }
}