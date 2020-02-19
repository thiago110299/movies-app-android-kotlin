package com.thiaguh11.movies.ui.movieslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thiaguh11.movies.databinding.GridItemRecyclerViewBinding
import com.thiaguh11.movies.models.Movie


class MoviesAdapter(private val onClickListener: OnClickListener) : ListAdapter<Movie, MoviesAdapter.MoviesViewHolder>(DiffCallback) {

    class MoviesViewHolder(private val binding : GridItemRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root){
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(GridItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(movie)
        }
        holder.bind(movie)
    }

    class OnClickListener(val clickListener: (movie: Movie) -> Unit){
        fun onClick(movie: Movie) = clickListener(movie)
    }

}