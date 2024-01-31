package com.example.movieapps.module

import com.example.movieapps.data.datasource.RemoteDataSource
import com.example.movieapps.domain.repository.Repository
import com.example.movieapps.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideGenreRepository(datasource: RemoteDataSource): Repository =
        RepositoryImpl(datasource)
    @Provides
    @Singleton
    fun provideListMovieRepository(datasource: RemoteDataSource): Repository =
        RepositoryImpl(datasource)

}