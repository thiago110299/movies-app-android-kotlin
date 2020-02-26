package com.thiaguh11.movies.ui.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.thiaguh11.movies.R
import com.thiaguh11.movies.models.Movie

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let { imageUrl ->
        val url = "http://image.tmdb.org/t/p/w185$imageUrl"
        Glide.with(imgView.context)
            .load(url)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

@BindingAdapter("listDataPopular")
fun bindRecyclerViewPopularList(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as MoviesListPopularAdapter
    adapter.submitList(data)
}

@BindingAdapter("listDataSearchResults")
fun bindRecyclerViewSearchResultsList(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as MoviesListResultsAdapter
    adapter.submitList(data)
}