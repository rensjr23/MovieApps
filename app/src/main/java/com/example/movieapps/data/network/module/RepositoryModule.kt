package com.example.movieapps.data.network.module

import com.example.movieapps.data.network.remote.GenreRemoteDataSource
import com.example.movieapps.data.network.repository.GenreRepository
import com.example.movieapps.data.network.repository.GenreRepositoryImpl
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
    fun provideGenreRepository(datasource: GenreRemoteDataSource): GenreRepository =
        GenreRepositoryImpl(datasource)
}