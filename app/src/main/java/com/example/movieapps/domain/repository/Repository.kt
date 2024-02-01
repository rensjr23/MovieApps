package com.example.movieapps.domain.repository

import com.example.movieapps.data.dto.ActorMovieResponse
import com.example.movieapps.data.dto.Genres
import com.example.movieapps.data.dto.Movie
import com.example.movieapps.data.dto.MovieDetailsResponse
import com.example.movieapps.data.dto.Movies
import com.example.movieapps.data.dto.ReviewResponse
import retrofit2.Response

interface Repository {
    suspend fun getListGenre(): Response<Genres>
    suspend fun getListMovie(genre: Int): Response<Movies>
    suspend fun getMovieDetails(movieId: Int): Response<MovieDetailsResponse>
    suspend fun getActorMovie(movieId: Int): Response<ActorMovieResponse>
    suspend fun getReviewMovie(movieId: Int): Response<ReviewResponse>
}