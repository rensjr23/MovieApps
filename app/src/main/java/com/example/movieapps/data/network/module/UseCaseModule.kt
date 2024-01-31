package com.example.movieapps.data.network.module

import com.example.movieapps.data.network.repository.GenreRepository
import com.example.movieapps.data.network.usecase.GenreUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetGenreUseCase(repository: GenreRepository)=
        GenreUseCase(repository)
}