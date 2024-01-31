package com.example.movieapps.data.dto

import com.google.gson.annotations.SerializedName

data class Genres(

	@field:SerializedName("genres")
	val genres: List<Genre?>? = null
)

data class Genre(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
