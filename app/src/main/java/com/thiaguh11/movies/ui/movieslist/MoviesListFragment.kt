package com.thiaguh11.movies.ui.movieslist


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.thiaguh11.movies.databinding.FragmentMoviesListBinding

/**
 * A simple [Fragment] subclass.
 */
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

        binding.recyclerView.adapter = MoviesAdapter(MoviesAdapter.OnClickListener{
            viewModel.displayMovieDetails(it)
        })

        viewModel.navigateToSelectedMovie.observe(this, Observer { movie ->
            if(movie != null) {
                findNavController().navigate(MoviesListFragmentDirections.actionMoviesListFragmentToMoviesDetailsFragment(movie))
                viewModel.displayMovieDetailsComplete()
            }
        })

        return binding.root
    }


}
