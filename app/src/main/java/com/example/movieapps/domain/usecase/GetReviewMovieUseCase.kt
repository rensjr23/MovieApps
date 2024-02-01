package com.example.movieapps.domain.usecase

import com.example.movieapps.domain.repository.Repository
import javax.inject.Inject

class GetReviewMovieUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(movieId: Int)=repository.getReviewMovie(movieId)
}