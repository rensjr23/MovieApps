package com.example.movieapps.data.network.remote

import com.example.movieapps.dto.GenreResponse
import retrofit2.Response

interface GenreRemoteDataSource {
    suspend fun getGenre(): Response<List<GenreResponse>>
}