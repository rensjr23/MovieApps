package com.example.movieapps.data.network.repository

import com.example.movieapps.data.network.remote.GenreRemoteDataSource
import com.example.movieapps.dto.GenreResponse
import retrofit2.Response
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val remoteDataSource: GenreRemoteDataSource
): GenreRepository {
    override suspend fun getGenre(): Response<List<GenreResponse>> {
        return remoteDataSource.getGenre()
    }
}