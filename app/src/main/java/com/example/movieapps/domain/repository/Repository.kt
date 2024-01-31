package com.example.movieapps.domain.repository

import com.example.movieapps.data.dto.Genres
import com.example.movieapps.data.dto.Movie
import com.example.movieapps.data.dto.Movies
import retrofit2.Response

interface Repository {
    suspend fun getListGenre(): Response<Genres>
    suspend fun getListMovie(genre: Int): Response<Movies>
}