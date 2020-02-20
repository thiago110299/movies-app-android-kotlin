package com.thiaguh11.movies.ui.moviesdetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thiaguh11.movies.models.Movie

class MoviesDetailsViewModel(movie: Movie, app: Application) : AndroidViewModel(app) {
    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    init {
        _movie.value = movie
    }

}