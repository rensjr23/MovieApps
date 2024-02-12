package com.example.movieapps.data.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.movieapps.data.dto.ActorMovieResponse
import com.example.movieapps.data.remote.MovieAPI
import com.example.movieapps.data.dto.Genres
import com.example.movieapps.data.dto.Movie
import com.example.movieapps.data.dto.MovieDetailsResponse
import com.example.movieapps.data.dto.Movies
import com.example.movieapps.data.dto.ReviewResponse
import com.example.movieapps.data.dto.SearchResponse
import com.example.movieapps.data.dto.VideoResponse
import com.example.movieapps.pagingsource.MoviePagingSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: MovieAPI,

): RemoteDataSource {
    override suspend fun getListGenre(): Response<Genres> {
        return api.getListGenre()
    }

    override suspend fun getListMovie(genre: Int): Flow<PagingData<Movie>> {
        val factory = MoviePagingSource(api, genre)
        Log.d("Rens", genre.toString())
        return Pager(
            config =
            PagingConfig(
                pageSize = 10, enablePlaceholders = false
            ),
            pagingSourceFactory = {
                factory
            }
        ).flow
    }


    override suspend fun getMovieDetails(movieId: Int): Response<MovieDetailsResponse> {
        return api.getMovieDetails(movieId)
    }

    override suspend fun getActorMovie(movieId: Int): Response<ActorMovieResponse> {
        return api.getActorMovie(movieId)
    }

    override suspend fun getReviewMovie(movieId: Int): Response<ReviewResponse> {
        return api.getReviewMovie(movieId)
    }

    override suspend fun getVideosMovie(movieId: Int): Response<VideoResponse> {
        return api.getVideosMovie(movieId)
    }

    override suspend fun getSearchMovie(query: String): Response<SearchResponse> {
        return api.getSearchMovie(query)
    }
}