package com.example.movieapps.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenreResponse (
    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("name")
    val name: String = "",
):Parcelable