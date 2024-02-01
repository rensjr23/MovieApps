package com.example.movieapps.module

import com.example.movieapps.domain.repository.Repository
import com.example.movieapps.domain.usecase.GetListGenreUseCase
import com.example.movieapps.domain.usecase.GetListMovieUseCase
import com.example.movieapps.domain.usecase.GetMovieDetailsUseCase
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
    fun provideGetGenreUseCase(repository: Repository)=
        GetListGenreUseCase(repository)
    @Provides
    @Singleton
    fun provideGetListMovieUseCase(repository: Repository)=
        GetListMovieUseCase(repository)

    @Provides
    @Singleton
    fun provideGetMovieDetailsUseCase(repository: Repository)=
        GetMovieDetailsUseCase(repository)
}