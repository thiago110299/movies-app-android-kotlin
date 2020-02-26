package com.thiaguh11.movies.ui.searchresults

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class SearchResultsViewModelFactory(
    private val query: String
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchResultsViewModel::class.java)) {
            return SearchResultsViewModel(query) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}