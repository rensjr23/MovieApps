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
) : ViewModel() {
    private val _genreData = MutableLiveData<List<Genre>>()
    val genreData: LiveData<List<Genre>>
        get() = _genreData

    private val _isLoading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val Error: LiveData<Boolean>
        get() = _isError

    init {
        _isLoading.postValue(true)
        _isError.postValue(false)
    }

    fun setGenreData() = viewModelScope.launch {

        try {
            _isLoading.postValue(true)
            val response = getListGenreUseCase()
            if (response.isSuccessful && response.body()?.genres != null) {
                response.body()?.genres?.let {
                    _isError.postValue(false)
                    _genreData.postValue(it.filterNotNull())
                }
            }
            _isLoading.postValue(false)
        } catch (e: Exception) {
            _isLoading.postValue(false)
            _isError.postValue(true)
        }
    }

}