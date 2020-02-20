package com.thiaguh11.movies.ui.moviesdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.thiaguh11.movies.databinding.FragmentMoviesDetailsBinding

class MoviesDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMoviesDetailsBinding.inflate(inflater)

        binding.lifecycleOwner = this

        val movie = MoviesDetailsFragmentArgs.fromBundle(arguments!!).movie
        val viewModelFactory = MoviesDetailsViewModelFactory(movie)

        binding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(MoviesDetailsViewModel::class.java)

        return binding.root
    }
}
