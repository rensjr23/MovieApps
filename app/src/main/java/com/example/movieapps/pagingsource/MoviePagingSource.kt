package com.example.movieapps.pagingsource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapps.data.datasource.RemoteDataSource
import com.example.movieapps.data.dto.Movie
import com.example.movieapps.data.remote.MovieAPI
import com.example.movieapps.domain.repository.Repository

class MoviePagingSource(
    private val apiService: MovieAPI,
    private val genre: Int
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: 1
        return try {
            val responseData =
                apiService.getListMovie(genre, position)
            val data = responseData.body()?.results ?: emptyList()
            Log.d("Rens", position.toString())
            LoadResult.Page(
                data = data,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (data.isNullOrEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}