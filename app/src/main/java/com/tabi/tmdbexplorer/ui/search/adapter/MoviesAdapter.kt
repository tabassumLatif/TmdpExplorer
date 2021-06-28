package com.tabi.tmdbexplorer.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tabi.tmdbexplorer.data.remote.models.Movie
import com.tabi.tmdbexplorer.databinding.ItemMovieBinding
import javax.inject.Inject

class MoviesAdapter @Inject constructor() : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    internal val movies: ArrayList<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun setMoviesList(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        lateinit var viewModel: MovieViewModel
        fun onBind(position: Int) {
            viewModel = MovieViewModel(movies[position])
            binding.viewModel = viewModel
        }

    }
}