package com.example.movieapps.presentation.view.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.Glide
import com.example.movieapps.R
import com.example.movieapps.data.dto.CastItem
import com.example.movieapps.data.dto.MovieDetailsResponse
import com.example.movieapps.data.dto.ResultsItem
import com.example.movieapps.databinding.FragmentMovieDetailsBinding
import com.example.movieapps.presentation.adapter.ActorMovieAdapter
import com.example.movieapps.presentation.adapter.ReviewMovieAdapter
import com.example.movieapps.presentation.base.BaseFragment
import com.example.movieapps.presentation.view.viewmodel.MovieDetailsViewModel
import com.example.movieapps.utils.DataReloadable
import com.example.movieapps.utils.setGone
import com.example.movieapps.utils.setVisible
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.ln
import kotlin.math.pow

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>(), DataReloadable {
    private val viewModel: MovieDetailsViewModel by viewModels()
    private var _actorMovieAdapter: ActorMovieAdapter? = null
    private var _reviewMovieAdapter: ReviewMovieAdapter? = null
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMovieDetailsBinding {
        return FragmentMovieDetailsBinding.inflate(inflater, container, false)
    }

    override fun setupView() {
        setupSuccess()
        setupError()
        observeViewModel()
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        setupSwipeRefreshException()
    }

    private fun setupSuccess() {
        viewModel.apply {
            loading.observe(viewLifecycleOwner) {
                setLoading(it)
            }
            getPassedGenreId()?.let {
                setMovieDetailsData(it)
                Log.d("Rens", it.toString())
            }
        }
    }

    private fun observeViewModel() {
        viewModel.movieDetailsData.observe(viewLifecycleOwner) {
            setupMovieDetails(it)
        }
        viewModel.actorMovieData.observe(viewLifecycleOwner) {
            setupViewActor(it)
        }
        viewModel.reviewMovieData.observe(viewLifecycleOwner) {
            setupReview(it)
        }
    }
    private fun setupError(){
        viewModel.Error.observe(viewLifecycleOwner){
            if (it){
                binding.tvReload.setVisible()
                binding.clDetails.setGone()
            }else{
                binding.tvReload.setGone()
                binding.clDetails.setVisible()
            }
        }
    }

    private fun getPassedGenreId(): Int? {
        return arguments?.getInt("movieId")
    }

    private fun setupViewActor(data: List<CastItem>) {
        _actorMovieAdapter = ActorMovieAdapter(data)
        binding.rvMovieImages.adapter = _actorMovieAdapter
    }

    private fun setupReview(data: List<ResultsItem>) {
        _reviewMovieAdapter = ReviewMovieAdapter(data)
        binding.rvReview.adapter = _reviewMovieAdapter
    }

    private fun setupMovieDetails(data: MovieDetailsResponse) {
        binding.tvMovieTitle.text = data.title
        binding.tvDuration.text = "${data.runtime} minutes"
        binding.tvTitle.text = data.originalTitle
        if (binding.tvBudget != null) {
            binding.tvBudget.text = "Budget: ${data.budget?.let { numberFormatter(it.toLong()) }}"
        } else {
            binding.tvBudget.visibility = View.GONE
        }
        val genre = StringBuilder()
        data.genres?.forEachIndexed { index, g ->
            genre.append("${g?.name}" + if (index != data.genres.size - 1) ", " else "")
        }
        binding.tvGenre.text = "Genres: $genre"
        val companies = StringBuilder()
        data.productionCompanies?.forEachIndexed { index, prCompany ->
            companies.append("${prCompany?.name}" + if (index != data.productionCompanies.size - 1) ", " else " ")
        }
        binding.tvCompanies.text = "Production companies: $companies"
        binding.tvDescription.text = data.overview
        binding.tvReleasedDate.text = "Released: ${data.releaseDate?.let { dateFormatter(it) }}"
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/${data.posterPath}")
            .error(R.drawable.ic_error)
            .into(binding.ivImage)
        binding.btnPlay.setOnClickListener {
            val bundle = bundleOf("id" to data.id)
            val action = MovieDetailsFragmentDirections.actionMovieDetailsFragmentToVideoFragment().actionId
            findNavController().navigate(action, bundle)
        }
        binding.ivPlay.setOnClickListener {
            val bundle = bundleOf("id" to data.id)
            val action = MovieDetailsFragmentDirections.actionMovieDetailsFragmentToVideoFragment().actionId
            findNavController().navigate(action, bundle)
        }
    }

    private fun numberFormatter(count: Long): String {
        if (count < 1000) return "" + count
        val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
        return String.format(
            "%.1f %c", count / 1000.0.pow(exp.toDouble()), "kMGTPE"[exp - 1]
        )
    }

    private fun dateFormatter(data: String): String {
        val incomingDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val showedDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
        val date = incomingDateFormat.parse(data)
        return showedDateFormat.format(date)
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
    private fun setupSwipeRefreshException(){
        val swipelayout:SwipeRefreshLayout = requireActivity().findViewById(R.id.swipeRefreshLayout)
        binding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (verticalOffset < -1) {
                swipelayout.isEnabled = false
            } else {
                swipelayout.isEnabled = true
            }
        })
    }

}