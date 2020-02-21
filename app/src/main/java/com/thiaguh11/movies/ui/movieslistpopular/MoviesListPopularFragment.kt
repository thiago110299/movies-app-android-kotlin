package com.thiaguh11.movies.ui.movieslistpopular

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.thiaguh11.movies.databinding.FragmentMoviesListBinding

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

        binding.recyclerView.adapter = MoviesAdapter(MoviesAdapter.OnClickListener{ movie ->
            popularViewModel.displayMovieDetails(movie)
        })

        popularViewModel.navigateToSelectedMovie.observe(this, Observer { selectedMovie ->
            selectedMovie?.let { movie ->
                findNavController().navigate(MoviesListPopularFragmentDirections.actionMoviesListFragmentToMoviesDetailsFragment(movie))
                popularViewModel.displayMovieDetailsComplete()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }
}
