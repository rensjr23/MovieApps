package com.example.movieapps.data.remote

import com.example.movieapps.data.dto.ActorMovieResponse
import com.example.movieapps.data.dto.Genres
import com.example.movieapps.data.dto.MovieDetailsResponse
import com.example.movieapps.data.dto.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {
    @GET("genre/movie/list?language=en")
    suspend fun getListGenre(
        @Query("api_key") apiKey: String = "5eb84100db044f614af0fed6ee89eb95"
    ): Response<Genres>

    @GET("discover/movie")
    suspend fun getListMovie(
        @Query("genre") genre: Int,
        @Query("api_key") apiKey: String = "5eb84100db044f614af0fed6ee89eb95"
    ): Response<Movies>
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "5eb84100db044f614af0fed6ee89eb95"
    ): Response<MovieDetailsResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun getActorMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "a0d35c93cf4be4650e17892fd16ab7a8"
    ): Response<ActorMovieResponse>
}