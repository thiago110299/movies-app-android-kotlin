package com.thiaguh11.movies.ui.searchresults

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.thiaguh11.movies.databinding.FragmentSearchResultsBinding
import com.thiaguh11.movies.ui.adapters.MoviesListResultsAdapter

class SearchResultsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentSearchResultsBinding.inflate(inflater)

        binding.lifecycleOwner = this

        val query = SearchResultsFragmentArgs.fromBundle(arguments!!).query
        val searchResultsViewModelFactory = SearchResultsViewModelFactory(query)

        val searchResultsViewModel = ViewModelProvider(this, searchResultsViewModelFactory).get(SearchResultsViewModel::class.java)

        binding.viewModel = searchResultsViewModel

        binding.recyclerView.adapter =
            MoviesListResultsAdapter(MoviesListResultsAdapter.OnClickListener { movie ->
                searchResultsViewModel.displayMovieDetails(movie)
            })

        searchResultsViewModel.resultsIsNull.observe(viewLifecycleOwner, Observer { resultsIsNull ->
            if(resultsIsNull)
                binding.labelNotFound.visibility = View.VISIBLE
        })

        searchResultsViewModel.navigateToSelectedMovie.observe(viewLifecycleOwner, Observer { selectedMovie ->
            selectedMovie?.let { movie ->
                findNavController().navigate(SearchResultsFragmentDirections.actionSearchResultsFragmentToMoviesDetailsFragment(movie))
                searchResultsViewModel.displayMovieDetailsComplete()
            }
        })

        return binding.root
    }

}
