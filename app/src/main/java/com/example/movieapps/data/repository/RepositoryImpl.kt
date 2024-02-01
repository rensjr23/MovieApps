package com.example.movieapps.data.repository

import com.example.movieapps.data.datasource.RemoteDataSource
import com.example.movieapps.data.dto.ActorMovieResponse
import com.example.movieapps.domain.repository.Repository
import com.example.movieapps.data.dto.Genres
import com.example.movieapps.data.dto.MovieDetailsResponse
import com.example.movieapps.data.dto.Movies
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): Repository {
    override suspend fun getListGenre(): Response<Genres> {
        return remoteDataSource.getListGenre()
    }

    override suspend fun getListMovie(genre: Int): Response<Movies> {
        return remoteDataSource.getListMovie(genre)
    }

    override suspend fun getMovieDetails(movieId: Int): Response<MovieDetailsResponse> {
        return remoteDataSource.getMovieDetails(movieId)
    }

    override suspend fun getActorMovie(movieId: Int): Response<ActorMovieResponse> {
        return remoteDataSource.getActorMovie(movieId)
    }
}