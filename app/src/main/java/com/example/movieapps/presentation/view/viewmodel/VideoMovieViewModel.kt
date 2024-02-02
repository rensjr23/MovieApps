package com.example.movieapps.presentation.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapps.data.dto.Results
import com.example.movieapps.domain.usecase.GetMovieDetailsUseCase
import com.example.movieapps.domain.usecase.GetVideosMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class VideoMovieViewModel @Inject constructor(
    private val getVideosMovieUseCase: GetVideosMovieUseCase
):ViewModel() {
    private val _videoMovieData = MutableLiveData<List<Results>>()
    val videoMovieData: LiveData<List<Results>>
        get() = _videoMovieData

    private val _isLoading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.postValue(true)
    }
    fun setVideoMovie(movieId:Int)=viewModelScope.launch{
        _isLoading.postValue(true)
        val response = getVideosMovieUseCase(movieId)

        if (response.isSuccessful && response.body()?.results != null) {
            _videoMovieData.postValue(response.body()!!.results?.filterNotNull())
        }

        _isLoading.postValue(false)

    }
}