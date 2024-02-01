package com.example.movieapps.presentation.view.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowId
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieDrawable
import com.example.movieapps.R
import com.example.movieapps.data.dto.Genre
import com.example.movieapps.databinding.FragmentHomeBinding
import com.example.movieapps.presentation.adapter.GenreAdapter
import com.example.movieapps.presentation.base.BaseFragment
import com.example.movieapps.presentation.view.viewmodel.GenreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel: GenreViewModel by viewModels()
    private var _genreAdapter: GenreAdapter? = null
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun setupView() {
        viewModel.apply {
            loading.observe(viewLifecycleOwner) {
                setLoading(it)
            }
            setGenreData()
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.genreData.observe(viewLifecycleOwner) {
            setupViewGenre(it)
        }
    }

    private fun setupViewGenre(data: List<Genre>) {
        _genreAdapter = GenreAdapter(data) {
            val bundle = bundleOf("id" to it.id, "title" to it.name)
            val action = HomeFragmentDirections.actionHomeFragmentToMovieFragment().actionId
            findNavController().navigate(action, bundle)
        }
        binding.componentGenre.gridGenre.adapter = _genreAdapter
    }


    private fun setLoading(isLoading: Boolean) {
        binding.loSplash.apply {
            visibility = if (isLoading) View.VISIBLE else View.GONE
            setAnimation(R.raw.lo_loadingnew)
            repeatCount = LottieDrawable.INFINITE
            playAnimation()
        }
    }

}