package com.example.movieapps.domain.usecase

import com.example.movieapps.domain.repository.Repository
import javax.inject.Inject

class GetListGenreUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke()=repository.getListGenre()

}