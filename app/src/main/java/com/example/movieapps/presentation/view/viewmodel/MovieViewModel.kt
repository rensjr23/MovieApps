package com.example.movieapps.presentation.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.example.movieapps.data.dto.Genre
import com.example.movieapps.data.dto.Movie
import com.example.movieapps.domain.usecase.GetListMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getListMovieUseCase: GetListMovieUseCase
) : ViewModel() {
    private val _movieData = MutableStateFlow<PagingData<Movie>?>(null)
    val movieData: StateFlow<PagingData<Movie>?>
        get() = _movieData
    private val _isError = MutableLiveData<Boolean>()
    val Error: LiveData<Boolean>
        get() = _isError

    init {
        _isError.value = false
    }

    fun setMovieData(genre: Int) = viewModelScope.launch {
        getListMovieUseCase(genre)
            .cachedIn(viewModelScope)
            .catch {
            _isError.postValue(true)
        }.collectLatest {
            _isError.postValue(false)
            _movieData.value = it
        }
    }

}