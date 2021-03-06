package com.thiaguh11.movies.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.thiaguh11.movies.database.MoviesDatabase
import com.thiaguh11.movies.database.asDomainModel
import com.thiaguh11.movies.models.Movie
import com.thiaguh11.movies.models.asDatabaseModel
import com.thiaguh11.movies.network.MoviesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(private val database: MoviesDatabase) {

    companion object {
        private const val apiKey = "############"
        private const val languagePTBR = "pt-br"
    }

    val movies: LiveData<List<Movie>> = Transformations.map(database.movieDao.getMovies()) { listDatabaseMovie ->
        listDatabaseMovie.asDomainModel()
    }

    suspend fun refreshMovies() {
        withContext(Dispatchers.IO) {
            val movies = MoviesApi.theMovieDB.getPopularMovies(apiKey, languagePTBR)
            database.movieDao.insertAll(movies.asDatabaseModel())
        }
    }
}
