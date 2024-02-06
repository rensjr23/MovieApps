package com.example.movieapps.presentation.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapps.data.dto.Genre
import com.example.movieapps.data.dto.Movie
import com.example.movieapps.domain.usecase.GetListMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getListMovieUseCase: GetListMovieUseCase
) : ViewModel() {
    private val _movieData = MutableLiveData<List<Movie>>()
    val movieData: LiveData<List<Movie>>
        get() = _movieData

    private val _isLoading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _isLoading
    private val _isError = MutableLiveData<Boolean>()
    val Error: LiveData<Boolean>
        get() = _isError

    init {
        _isLoading.postValue(true)
    }

    fun setMovieData(genre: Int) = viewModelScope.launch {
        _isError.postValue(false)
        _isLoading.postValue(true)
        try {
            val response = getListMovieUseCase(genre)

            if (response.isSuccessful && response.body()?.results != null) {
                response.body()?.results?.let {
                    _movieData.postValue(it.filterNotNull())
                }
            } else {
                _movieData.postValue(emptyList())
            }
            _isError.postValue(false)
            _isLoading.postValue(false)
        } catch (e: Exception) {
            _isLoading.postValue(false)
            _isError.postValue(true)
        }
    }
}