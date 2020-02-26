package com.thiaguh11.movies.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thiaguh11.movies.databinding.GridItemRecyclerViewBinding
import com.thiaguh11.movies.models.Movie


class MoviesListPopularAdapter(private val onClickListener: OnClickListener) : ListAdapter<Movie, MoviesListPopularAdapter.MoviesListPopularViewHolder>(
    DiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListPopularViewHolder {
        return MoviesListPopularViewHolder(
            GridItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: MoviesListPopularViewHolder, position: Int) {
        val movie = getItem(position)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(movie)
        }
        holder.bind(movie)
    }

    class OnClickListener(val clickListener: (movie: Movie) -> Unit){
        fun onClick(movie: Movie) = clickListener(movie)
    }

    class MoviesListPopularViewHolder(private val binding : GridItemRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
    }

}