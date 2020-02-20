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
        private const val apiKey = "bc2e3da040e08c84bf35240d7d32cb59"
    }

    val movies: LiveData<List<Movie>> = Transformations.map(database.movieDao.getMovies()) { ListDatabaseMovie ->
        ListDatabaseMovie.asDomainModel()
    }

    suspend fun refreshMovies() {
        withContext(Dispatchers.IO) {
            val movies = MoviesApi.retrofitService.getPopularMovies(apiKey, "pt-br")
            database.movieDao.insertAll(movies.asDatabaseModel())
        }
    }
}