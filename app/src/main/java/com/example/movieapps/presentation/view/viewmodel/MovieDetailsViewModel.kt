package com.example.movieapps.presentation.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapps.data.dto.Genre
import com.example.movieapps.data.dto.MovieDetailsResponse
import com.example.movieapps.domain.usecase.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {
    private val _movieDetailsData = MutableLiveData<MovieDetailsResponse>()
    val movieDetailsData: LiveData<MovieDetailsResponse>
        get() = _movieDetailsData

    private val _isLoading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.postValue(true)
    }

    fun setMovieDetailsData(movieId: Int) = viewModelScope.launch {
        _isLoading.postValue(true)
        val response = getMovieDetailsUseCase(movieId)

        if (response.isSuccessful && response.body()?.genres != null) {
            _movieDetailsData.postValue(response.body())
        }
        _isLoading.postValue(false)
    }
}