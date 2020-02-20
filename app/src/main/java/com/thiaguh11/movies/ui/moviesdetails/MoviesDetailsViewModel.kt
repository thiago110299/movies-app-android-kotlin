package com.thiaguh11.movies.ui.moviesdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thiaguh11.movies.models.Movie

class MoviesDetailsViewModel(movie: Movie) : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    init {
        _movie.value = movie
    }

}