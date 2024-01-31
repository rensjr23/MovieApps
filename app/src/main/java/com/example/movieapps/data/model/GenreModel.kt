package com.example.movieapps.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenreModel(
    val id: Int,
    val name: String
):Parcelable
