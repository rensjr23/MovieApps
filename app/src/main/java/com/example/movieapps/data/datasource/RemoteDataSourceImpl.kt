package com.example.movieapps.data.datasource

import com.example.movieapps.data.dto.ActorMovieResponse
import com.example.movieapps.data.remote.MovieAPI
import com.example.movieapps.data.dto.Genres
import com.example.movieapps.data.dto.MovieDetailsResponse
import com.example.movieapps.data.dto.Movies
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: MovieAPI
): RemoteDataSource {
    override suspend fun getListGenre(): Response<Genres> {
        return api.getListGenre()
    }
    override suspend fun getListMovie(genre: Int): Response<Movies> {
        return api.getListMovie(genre)
    }

    override suspend fun getMovieDetails(movieId: Int): Response<MovieDetailsResponse> {
        return api.getMovieDetails(movieId)
    }

    override suspend fun getActorMovie(movieId: Int): Response<ActorMovieResponse> {
        return api.getActorMovie(movieId)
    }
}