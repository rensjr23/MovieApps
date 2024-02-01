package com.example.movieapps.presentation.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapps.domain.usecase.GetListGenreUseCase
import com.example.movieapps.data.dto.Genre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(
    private val getListGenreUseCase: GetListGenreUseCase
): ViewModel(){
    private val _genreData = MutableLiveData<List<Genre>>()
    val genreData: LiveData<List<Genre>>
        get() = _genreData

    private val _isLoading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.postValue(true)
    }

    fun setGenreData() = viewModelScope.launch {
        _isLoading.postValue(true)
        val response = getListGenreUseCase()

        if (response.isSuccessful && response.body()?.genres != null) {
            _genreData.postValue(response.body()!!.genres?.filterNotNull())
        } else {
            _genreData.postValue(emptyList())
        }

        _isLoading.postValue(false)
    }
}