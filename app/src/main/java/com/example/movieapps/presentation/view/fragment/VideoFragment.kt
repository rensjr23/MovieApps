package com.example.movieapps.presentation.view.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.movieapps.databinding.FragmentVideoBinding
import com.example.movieapps.presentation.base.BaseFragment
import com.example.movieapps.presentation.view.viewmodel.VideoMovieViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoFragment : BaseFragment<FragmentVideoBinding>() {
    private val viewModel:VideoMovieViewModel by viewModels()
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentVideoBinding {
        return FragmentVideoBinding.inflate(inflater, container, false)
    }

    override fun setupView() {
        viewModel.apply {
            getPassedGenreId()?.let {
                setVideoMovie(it)
            }
        }
        observeViewModel()
    }
    private fun getPassedGenreId(): Int? {
        return arguments?.getInt("id")
    }
    private fun observeViewModel() {
        val ytPlayer = binding.youtubeVideo
        viewModel.videoMovieData.observe(viewLifecycleOwner){videos->
            val teaserOrTrailer = videos.find { it.type == "Teaser" || it.type == "Trailer" }
            if (teaserOrTrailer != null) {
                ytPlayer.addYouTubePlayerListener(object :
                    AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        teaserOrTrailer.key?.let { youTubePlayer.loadVideo(it, 0f) }
                    }
                })
            }

        }
    }

}