package com.example.movieapps.domain.usecase

import com.example.movieapps.domain.repository.Repository
import javax.inject.Inject

class GetSearchMovieUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(query: String)=repository.getSearchMovie(query)
}