package com.thiaguh11.movies.ui.movieslist

import android.app.Application
import androidx.lifecycle.*
import com.thiaguh11.movies.database.getDatabase
import com.thiaguh11.movies.models.Movie
import com.thiaguh11.movies.repository.MoviesRepository
import com.thiaguh11.movies.util.ApiStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException

class MoviesListViewModel(app: Application) : AndroidViewModel(app) {

    private val moviesRepository = MoviesRepository(getDatabase(app))

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    val movies: LiveData<List<Movie>> = moviesRepository.movies

    private val _navigateToSelectedMovie = MutableLiveData<Movie>()
    val navigateToSelectedMovie: LiveData<Movie>
        get() = _navigateToSelectedMovie

    private val viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getMoviesFromRepository()
    }

    private fun getMoviesFromRepository() {
        coroutineScope.launch {
            try {
                _status.value = ApiStatus.LOADING
                moviesRepository.refreshMovies()
                _status.value = ApiStatus.DONE
            } catch (e: IOException) {
                _status.value = ApiStatus.ERROR
            }
        }
    }

    fun displayMovieDetails(movie: Movie) {
        _navigateToSelectedMovie.value = movie
    }

    fun displayMovieDetailsComplete() {
        _navigateToSelectedMovie.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}