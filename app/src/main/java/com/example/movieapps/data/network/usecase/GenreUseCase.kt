package com.example.movieapps.data.network.usecase

import com.example.movieapps.data.model.GenreModel
import com.example.movieapps.data.network.repository.GenreRepository
import com.example.movieapps.dto.GenreResponse
import javax.inject.Inject

class GenreUseCase @Inject constructor(
    private val repository: GenreRepository
) {
    suspend fun getGenre(): List<GenreModel>?{
        repository.getGenre().apply {
            if (isSuccessful){
                return body()?.let { mappingRemoteToModel(it) }
            }
            return listOf()
        }
    }
    suspend fun mappingRemoteToModel(response: List<GenreResponse>):List<GenreModel>{
        return response.mapNotNull { genreResponse ->
            genreResponse?.let {
                GenreModel(
                    id = it.id,
                    name = it.name
                )
            }
        }
    }

}