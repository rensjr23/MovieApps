package com.example.movieapps.data.datasource

import com.example.movieapps.data.dto.ActorMovieResponse
import com.example.movieapps.data.dto.Genres
import com.example.movieapps.data.dto.MovieDetailsResponse
import com.example.movieapps.data.dto.Movies
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getListGenre(): Response<Genres>
    suspend fun getListMovie(genre: Int): Response<Movies>
    suspend fun getMovieDetails(movieId: Int): Response<MovieDetailsResponse>
    suspend fun getActorMovie(movieId: Int): Response<ActorMovieResponse>
}