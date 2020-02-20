package com.thiaguh11.movies.ui.moviesdetails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thiaguh11.movies.models.Movie
import java.lang.IllegalArgumentException

class MoviesDetailsViewModelFactory(
    private val movie: Movie,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesDetailsViewModel::class.java)) {
            return MoviesDetailsViewModel(movie, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}