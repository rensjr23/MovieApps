package com.example.movieapps.data.remote

import com.example.movieapps.data.dto.Genres
import com.example.movieapps.data.dto.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    @GET("genre/movie/list?language=en")
    suspend fun getListGenre(
        @Query("api_key") apiKey: String = "5eb84100db044f614af0fed6ee89eb95"
    ): Response<Genres>

    @GET("discover/movie")
    suspend fun getListMovieByGenre(
        @Query("with_genres") genre: Int
    ): Response<Movies>
}