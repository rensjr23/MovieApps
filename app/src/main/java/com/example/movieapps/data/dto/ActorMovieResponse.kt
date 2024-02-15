package com.example.movieapps.data.dto

import com.google.gson.annotations.SerializedName

data class ActorMovieResponse(

	@SerializedName("cast")
	val cast: List<CastItem?>? = null,

	@SerializedName("id")
	val id: Int? = null,

	@SerializedName("crew")
	val crew: List<CrewItem?>? = null
)

data class CastItem(

	@SerializedName("cast_id")
	val castId: Int? = null,

	@SerializedName("character")
	val character: String? = null,

	@SerializedName("gender")
	val gender: Int? = null,

	@SerializedName("credit_id")
	val creditId: String? = null,

	@SerializedName("known_for_department")
	val knownForDepartment: String? = null,

	@SerializedName("original_name")
	val originalName: String? = null,

	@SerializedName("popularity")
	val popularity: Double? = null,

	@SerializedName("name")
	val name: String? = null,

	@SerializedName("profile_path")
	val profilePath: String? = null,

	@SerializedName("id")
	val id: Int? = null,

	@SerializedName("adult")
	val adult: Boolean? = null,

	@SerializedName("order")
	val order: Int? = null
)

data class CrewItem(

	@SerializedName("gender")
	val gender: Int? = null,

	@SerializedName("credit_id")
	val creditId: String? = null,

	@SerializedName("known_for_department")
	val knownForDepartment: String? = null,

	@SerializedName("original_name")
	val originalName: String? = null,

	@SerializedName("popularity")
	val popularity: Double? = null,

	@SerializedName("name")
	val name: String? = null,

	@SerializedName("profile_path")
	val profilePath: String? = null,

	@SerializedName("id")
	val id: Int? = null,

	@SerializedName("adult")
	val adult: Boolean? = null,

	@SerializedName("department")
	val department: String? = null,

	@SerializedName("job")
	val job: String? = null
)
