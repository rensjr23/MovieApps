package com.example.movieapps.presentation.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapps.data.dto.CastItem
import com.example.movieapps.data.dto.Genre
import com.example.movieapps.data.dto.MovieDetailsResponse
import com.example.movieapps.data.dto.ResultsItem
import com.example.movieapps.domain.usecase.GetActorMovieUseCase
import com.example.movieapps.domain.usecase.GetMovieDetailsUseCase
import com.example.movieapps.domain.usecase.GetReviewMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getActorMovieUseCase: GetActorMovieUseCase,
    private val getReviewMovieUseCase: GetReviewMovieUseCase
) : ViewModel() {
    private val _movieDetailsData = MutableLiveData<MovieDetailsResponse>()
    private val _actorMovieData = MutableLiveData<List<CastItem>>()
    private val _reviewMovieData = MutableLiveData<List<ResultsItem>>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _isError = MutableLiveData<Boolean>()
    val movieDetailsData: LiveData<MovieDetailsResponse>
        get() = _movieDetailsData
    val actorMovieData: LiveData<List<CastItem>>
        get() = _actorMovieData
    val reviewMovieData: LiveData<List<ResultsItem>>
        get() = _reviewMovieData

    val loading: LiveData<Boolean>
        get() = _isLoading
    val Error: LiveData<Boolean>
        get() = _isError

    init {
        _isLoading.postValue(true)
    }

    fun setMovieDetailsData(movieId: Int) = viewModelScope.launch {
        _isError.postValue(false)
        _isLoading.postValue(true)
        try {

            val resDetails = getMovieDetailsUseCase(movieId)
            val resActor = getActorMovieUseCase(movieId)
            val resReview = getReviewMovieUseCase(movieId)

            if (resActor.isSuccessful && resActor.body()?.cast != null) {
                resActor.body()?.cast?.let {
                    _actorMovieData.postValue(it.filterNotNull())
                }
            }
            if (resReview.isSuccessful && resReview.body()?.results != null) {
                resReview.body()?.results?.let {
                    _reviewMovieData.postValue(it.filterNotNull())
                }
            }
            if (resDetails.isSuccessful && resDetails.body()?.genres != null) {
                _movieDetailsData.postValue(resDetails.body())
            }
            _isLoading.postValue(false)
        }catch (e: Exception) {
            _isLoading.postValue(false)
            _isError.postValue(true)
        }
    }
}