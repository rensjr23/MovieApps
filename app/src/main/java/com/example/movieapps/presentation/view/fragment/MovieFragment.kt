package com.example.movieapps.presentation.view.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieDrawable
import com.example.movieapps.R
import com.example.movieapps.data.dto.Movie
import com.example.movieapps.databinding.FragmentMovieBinding
import com.example.movieapps.presentation.adapter.MovieAdapter
import com.example.movieapps.presentation.base.BaseFragment
import com.example.movieapps.presentation.view.viewmodel.MovieViewModel
import com.example.movieapps.utils.DataReloadable
import dagger.hilt.android.AndroidEntryPoint

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
    }

    private fun observeViewModel() {
        viewModel.movieData.observe(viewLifecycleOwner) {
            setupViewMovie(it)
        }
    }
    private fun setupSuccess(){
        viewModel.apply {
            loading.observe(viewLifecycleOwner) {
                setLoading(it)
            }
            getPassedGenreId()?.let {
                setMovieData(
                    it
                )
            }
        }
    }
    private fun setupError(){
        viewModel.Error.observe(viewLifecycleOwner){
            if (it){
                binding.tvReload.visibility = View.VISIBLE
                binding.tvTitlePage.visibility = View.GONE
                binding.componentMovie.gridMovie.visibility = View.GONE
            }else{
                binding.tvReload.visibility = View.GONE
                binding.tvTitlePage.visibility = View.VISIBLE
                binding.componentMovie.gridMovie.visibility = View.VISIBLE
            }
        }
    }

    private fun getPassedGenreId(): Int? {
        return arguments?.getInt("id")
    }

    private fun setupViewMovie(data: List<Movie>) {
        _movieAdapter = MovieAdapter(data) { id->
            val bundle = bundleOf("movieId" to id)
            val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment().actionId
            findNavController().navigate(action, bundle)
        }

        val title = arguments?.getString("title")
        binding.componentMovie.gridMovie.adapter = _movieAdapter
        binding.tvTitlePage.text = "Genre ${title}"
    }

    private fun setLoading(isLoading: Boolean) {
        binding.loSplash.apply {
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