package com.example.movieapps.presentation.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapps.data.dto.CastItem
import com.example.movieapps.data.dto.Genre
import com.example.movieapps.data.dto.MovieDetailsResponse
import com.example.movieapps.domain.usecase.GetActorMovieUseCase
import com.example.movieapps.domain.usecase.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getActorMovieUseCase: GetActorMovieUseCase
) : ViewModel() {
    private val _movieDetailsData = MutableLiveData<MovieDetailsResponse>()
    val movieDetailsData: LiveData<MovieDetailsResponse>
        get() = _movieDetailsData
    private val _actorMovieData = MutableLiveData<List<CastItem>>()
    val actorMovieData: LiveData<List<CastItem>>
        get() = _actorMovieData

    private val _isLoading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.postValue(true)
    }

    fun setMovieDetailsData(movieId: Int) = viewModelScope.launch {
        _isLoading.postValue(true)
        val resDetails = getMovieDetailsUseCase(movieId)
        val resActor = getActorMovieUseCase(movieId)

        if (resActor.isSuccessful && resActor.body()?.cast != null){
            _actorMovieData.postValue(resActor.body()!!.cast?.filterNotNull())
        }

        if (resDetails.isSuccessful && resDetails.body()?.genres != null) {
            _movieDetailsData.postValue(resDetails.body())
        }
        _isLoading.postValue(false)
    }
}