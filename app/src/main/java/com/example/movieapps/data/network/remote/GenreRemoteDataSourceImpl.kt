package com.example.movieapps.data.network.remote

import com.example.movieapps.data.MovieAPI
import com.example.movieapps.dto.GenreResponse
import retrofit2.Response
import javax.inject.Inject

class GenreRemoteDataSourceImpl @Inject constructor(
    private val api: MovieAPI
):GenreRemoteDataSource {
    override suspend fun getGenre(): Response<List<GenreResponse>> {
        return api.getGenre()
    }
}