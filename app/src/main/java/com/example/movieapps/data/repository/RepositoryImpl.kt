package com.example.movieapps.data.repository

import com.example.movieapps.data.datasource.RemoteDataSource
import com.example.movieapps.data.dto.ActorMovieResponse
import com.example.movieapps.domain.repository.Repository
import com.example.movieapps.data.dto.Genres
import com.example.movieapps.data.dto.MovieDetailsResponse
import com.example.movieapps.data.dto.Movies
import com.example.movieapps.data.dto.ReviewResponse
import com.example.movieapps.data.dto.VideoResponse
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

    override suspend fun getReviewMovie(movieId: Int): Response<ReviewResponse> {
        return remoteDataSource.getReviewMovie(movieId)
    }

    override suspend fun getVideosMovie(movieId: Int): Response<VideoResponse> {
        return remoteDataSource.getVideosMovie(movieId)
    }
}