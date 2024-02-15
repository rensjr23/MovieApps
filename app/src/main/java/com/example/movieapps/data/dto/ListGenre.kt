package com.example.movieapps.data.dto

import com.google.gson.annotations.SerializedName

data class Genres(

	@SerializedName("genres")
	val genres: List<Genre?>? = null
)

data class Genre(

	@SerializedName("name")
	val name: String? = null,

	@SerializedName("id")
	val id: Int? = null
)
