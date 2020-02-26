package com.thiaguh11.movies.ui.searchresults

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thiaguh11.movies.models.Movie
import com.thiaguh11.movies.network.MoviesApi.theMovieDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchResultsViewModel(val query: String) : ViewModel() {

    companion object {
        const val apiKey = "bc2e3da040e08c84bf35240d7d32cb59"
        const val languagePTBR = "pt-br"
    }

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    private val _navigateToSelectedMovie = MutableLiveData<Movie>()
    val navigateToSelectedMovie: LiveData<Movie>
        get() = _navigateToSelectedMovie

    private val viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _resultsIsNull = MutableLiveData<Boolean>()
    val resultsIsNull: LiveData<Boolean> get() = _resultsIsNull

    init {
        searchMovies()
    }

    private fun searchMovies() {
        coroutineScope.launch {
            try {
                val listResults = theMovieDB.getMovie(apiKey, languagePTBR, query).results
                listResults.let { results ->
                    _movies.value = results
                    _resultsIsNull.value = false
                }
            } catch (e: Exception) {
                _resultsIsNull.value = true
                _movies.value = ArrayList()
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