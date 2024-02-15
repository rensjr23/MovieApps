package com.example.movieapps.module

import com.example.movieapps.data.remote.MovieAPI
import com.example.movieapps.data.datasource.RemoteDataSource
import com.example.movieapps.data.datasource.RemoteDataSourceImpl
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
    fun provideGenreRemoteDataSource(movieAPI: MovieAPI): RemoteDataSource =
        RemoteDataSourceImpl(movieAPI)

}