package com.example.movieapps.data.network.repository

import com.example.movieapps.dto.GenreResponse
import retrofit2.Response

interface GenreRepository {
    suspend fun getGenre(): Response<List<GenreResponse>>
}