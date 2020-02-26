package com.thiaguh11.movies.ui.movieslistpopular

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.thiaguh11.movies.R
import com.thiaguh11.movies.databinding.FragmentMoviesListBinding
import com.thiaguh11.movies.ui.adapters.MoviesListPopularAdapter

class MoviesListPopularFragment : Fragment() {

    private val popularViewModel: MoviesListPopularViewModel by lazy {
        ViewModelProviders.of(this).get(MoviesListPopularViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMoviesListBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = popularViewModel

        binding.recyclerView.adapter =
            MoviesListPopularAdapter(MoviesListPopularAdapter.OnClickListener { movie ->
                popularViewModel.displayMovieDetails(movie)
            })

        popularViewModel.navigateToSelectedMovie.observe(viewLifecycleOwner, Observer { selectedMovie ->
            selectedMovie?.let { movie ->
                findNavController().navigate(MoviesListPopularFragmentDirections.actionMoviesListFragmentToMoviesDetailsFragment(movie))
                popularViewModel.displayMovieDetailsComplete()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val manager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(activity?.componentName))

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()

                query?.let { queryMovie ->
                    findNavController().navigate(MoviesListPopularFragmentDirections.actionMoviesListFragmentToSearchResultsFragment(queryMovie))
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return super.onCreateOptionsMenu(menu, inflater)
    }
}
