package com.example.movieapps.presentation.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapps.data.dto.SearchItem
import com.example.movieapps.domain.usecase.GetSearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMovieViewModel @Inject constructor(
    private val getSearchMovieUseCase: GetSearchMovieUseCase
):ViewModel(){
    private val _searchMovieData = MutableLiveData<List<SearchItem>>()
    val searchMovieData: LiveData<List<SearchItem>>
        get() = _searchMovieData

    private val _isLoading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.postValue(true)
    }

    fun setSearchMovie(query: String) = viewModelScope.launch {
        _isLoading.postValue(true)
        val response = getSearchMovieUseCase(query)

        if (response.isSuccessful && response.body()?.results != null) {
            _searchMovieData.postValue(response.body()!!.results?.filterNotNull())
        }

        _isLoading.postValue(false)

    }
}