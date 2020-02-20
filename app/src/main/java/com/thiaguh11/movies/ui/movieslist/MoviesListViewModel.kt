package com.thiaguh11.movies.ui.movieslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thiaguh11.movies.models.Movie
import com.thiaguh11.movies.models.PopularResponse
import com.thiaguh11.movies.network.MoviesApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

enum class ApiStatus {LOADING, ERROR, DONE}
private val apiKey = "bc2e3da040e08c84bf35240d7d32cb59"

class MoviesListViewModel : ViewModel() {
    companion object {
        const val TAG = "MoviesListViewModel"
    }

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    private val _navigateToSelectedMovie = MutableLiveData<Movie>()
    val navigateToSelectedMovie: LiveData<Movie>
        get() = _navigateToSelectedMovie

    private val viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getMoviesFromNetwork()
    }

    private fun getMoviesFromNetwork() {
        coroutineScope.launch {
            try {
                _status.value = ApiStatus.LOADING
                val popularResponse: PopularResponse = MoviesApi.retrofitService.getPopularMovies(apiKey, "pt-br")
                _movies.value = popularResponse.results
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _movies.value = ArrayList()
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