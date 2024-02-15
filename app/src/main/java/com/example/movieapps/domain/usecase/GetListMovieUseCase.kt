package com.example.movieapps.domain.usecase

import com.example.movieapps.domain.repository.Repository
import javax.inject.Inject

class GetListMovieUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(genre: Int)=repository.getListMovie(genre)
}