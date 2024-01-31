package com.example.movieapps.data.datasource

import com.example.movieapps.data.remote.MovieAPI
import com.example.movieapps.data.dto.Genres
import com.example.movieapps.data.dto.Movies
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: MovieAPI
): RemoteDataSource {
    override suspend fun getListGenre(): Response<Genres> {
        return api.getListGenre()
    }

    override suspend fun getMovieByGenre(genre: Int): Response<Movies> {
        return api.getListMovieByGenre(genre)
    }
}