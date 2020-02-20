package com.thiaguh11.movies.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.thiaguh11.movies.models.Movie

@Entity
data class DatabaseMovie(
    @PrimaryKey
    val id: Int,
    val title: String,
    val overview: String,
    @Json(name = "release-date")
    val releaseDate: String,
    @Json(name = "poster_path")
    val imageUrl: String
)

fun List<DatabaseMovie>.asDomainModel() : List<Movie> {
    return map { movie ->
        Movie(
            id = movie.id,
            title = movie.title,
            overview = movie.overview,
            imageUrl = movie.imageUrl,
            releaseDate = movie.releaseDate
        )
    }
}