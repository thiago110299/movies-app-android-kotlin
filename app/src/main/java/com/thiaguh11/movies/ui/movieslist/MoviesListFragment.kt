package com.thiaguh11.movies.ui.movieslist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.thiaguh11.movies.databinding.FragmentMoviesListBinding

class MoviesListFragment : Fragment() {

    private val viewModel: MoviesListViewModel by lazy {
        ViewModelProviders.of(this).get(MoviesListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMoviesListBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.recyclerView.adapter = MoviesAdapter(MoviesAdapter.OnClickListener{ movie ->
            viewModel.displayMovieDetails(movie)
        })

        viewModel.navigateToSelectedMovie.observe(this, Observer { selectedMovie ->
            selectedMovie?.let { movie ->
                findNavController().navigate(MoviesListFragmentDirections.actionMoviesListFragmentToMoviesDetailsFragment(movie))
                viewModel.displayMovieDetailsComplete()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }
}
