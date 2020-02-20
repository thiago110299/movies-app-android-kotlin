package com.thiaguh11.movies.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PopularResponse(
    val page: Int,
    val results: List<Movie>
): Parcelable