package com.thiaguh11.movies.models

import android.os.Parcelable
import com.thiaguh11.movies.database.DatabaseMovie
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PopularResponse(
    val page: Int,
    val results: List<Movie>
): Parcelable

fun PopularResponse.asDatabaseModel() : List<DatabaseMovie> {
    return results.map { movie ->
        DatabaseMovie(
            id = movie.id,
            title = movie.title,
            imageUrl = movie.imageUrl,
            overview = movie.overview,
            releaseDate = movie.releaseDate
        )
    }
}
