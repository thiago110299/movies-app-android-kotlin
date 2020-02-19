package com.thiaguh11.movies.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "poster_path")
    val imageUrl: String
) : Parcelable