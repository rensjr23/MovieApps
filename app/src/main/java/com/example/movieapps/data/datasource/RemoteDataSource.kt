package com.example.movieapps.data.datasource

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.movieapps.data.dto.ActorMovieResponse
import com.example.movieapps.data.dto.Genres
import com.example.movieapps.data.dto.Movie
import com.example.movieapps.data.dto.MovieDetailsResponse
import com.example.movieapps.data.dto.Movies
import com.example.movieapps.data.dto.ReviewResponse
import com.example.movieapps.data.dto.SearchResponse
import com.example.movieapps.data.dto.VideoResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getListGenre(): Response<Genres>
    suspend fun getListMovie(genre: Int): Flow<PagingData<Movie>>
    suspend fun getMovieDetails(movieId: Int): Response<MovieDetailsResponse>
    suspend fun getActorMovie(movieId: Int): Response<ActorMovieResponse>
    suspend fun getReviewMovie(movieId: Int): Response<ReviewResponse>
    suspend fun getVideosMovie(movieId: Int): Response<VideoResponse>
    suspend fun getSearchMovie(query: String): Response<SearchResponse>
}