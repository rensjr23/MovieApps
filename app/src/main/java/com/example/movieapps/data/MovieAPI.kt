package com.example.movieapps.data

import com.example.movieapps.dto.GenreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    @GET("/genre/movie/list")
    suspend fun getGenre(
        @Query("api_key") apiKey: String = "5eb84100db044f614af0fed6ee89eb95"
    ): Response<List<GenreResponse>>
}