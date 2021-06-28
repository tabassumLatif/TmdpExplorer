package com.tabi.tmdbexplorer.util

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tabi.tmdbexplorer.data.remote.models.Movie
import com.tabi.tmdbexplorer.ui.search.adapter.MoviesAdapter

class BindingUtil {
    companion object{
        @JvmStatic
        @BindingAdapter("setMovies")
        fun setMovies(recyclerView: RecyclerView, items: List<Movie>?) {
            val moviesAdapter: MoviesAdapter? =
                recyclerView.adapter as MoviesAdapter?
            Log.d("tabi_adapter", "setMovies: "+items?.size)
            if (moviesAdapter != null && items != null) {
                moviesAdapter.setMoviesList(items)
            }
        }
    }
}