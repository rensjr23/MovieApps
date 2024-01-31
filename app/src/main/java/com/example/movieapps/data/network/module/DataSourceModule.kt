package com.example.movieapps.data.network.module

import com.example.movieapps.data.MovieAPI
import com.example.movieapps.data.network.remote.GenreRemoteDataSource
import com.example.movieapps.data.network.remote.GenreRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideGenreRemoteDataSource(movieAPI: MovieAPI): GenreRemoteDataSource=
        GenreRemoteDataSourceImpl(movieAPI)
}