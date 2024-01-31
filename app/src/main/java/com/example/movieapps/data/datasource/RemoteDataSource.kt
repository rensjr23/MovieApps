package com.example.movieapps.data.datasource

import com.example.movieapps.data.dto.Genres
import com.example.movieapps.data.dto.Movies
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getListGenre(): Response<Genres>

    suspend fun getMovieByGenre(genre: Int): Response<Movies>
}