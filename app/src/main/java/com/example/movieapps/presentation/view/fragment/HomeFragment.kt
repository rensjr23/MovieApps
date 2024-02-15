package com.example.movieapps.presentation.view.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieDrawable
import com.example.movieapps.R
import com.example.movieapps.data.dto.Genre
import com.example.movieapps.databinding.FragmentHomeBinding
import com.example.movieapps.presentation.adapter.GenreAdapter
import com.example.movieapps.presentation.base.BaseFragment
import com.example.movieapps.presentation.view.viewmodel.GenreViewModel
import com.example.movieapps.utils.DataReloadable
import com.example.movieapps.utils.setGone
import com.example.movieapps.utils.setVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), DataReloadable {
    private val viewModel: GenreViewModel by viewModels()
    private var _genreAdapter: GenreAdapter? = null
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun setupView() {
        setupSuccess()
        setupError()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.genreData.observe(viewLifecycleOwner) {
            setupViewGenre(it)
        }
    }

    private fun setupViewGenre(data: List<Genre>) {
        _genreAdapter = GenreAdapter(data) {genre->
            val bundle = bundleOf("id" to genre.id, "title" to genre.name)
            val action = HomeFragmentDirections.actionHomeFragmentToMovieFragment().actionId
            findNavController().navigate(action, bundle)
        }
        binding.componentGenre.gridGenre.adapter = _genreAdapter
    }
    private fun setupSuccess(){
        viewModel.apply {
            loading.observe(viewLifecycleOwner) {
                setLoading(it)
            }
            setGenreData()
        }
    }
    private fun setupError(){
        viewModel.Error.observe(viewLifecycleOwner){
            if (it){
                binding.tvReload.setVisible()
                binding.tvTitle.setGone()
                binding.componentGenre.gridGenre.setGone()
            }else{
                binding.tvReload.setGone()
                binding.tvTitle.setVisible()
                binding.componentGenre.gridGenre.setVisible()
            }
        }
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