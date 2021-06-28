package com.tabi.tmdbexplorer.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tabi.tmdbexplorer.BuildConfig.IMAGE_BASE_URL
import com.tabi.tmdbexplorer.R
import com.tabi.tmdbexplorer.data.remote.models.Movie
import com.tabi.tmdbexplorer.ui.search.adapter.MoviesAdapter


class BindingUtil {
    companion object {
        @JvmStatic
        @BindingAdapter("setMovies")
        fun setMovies(recyclerView: RecyclerView, items: List<Movie>?) {
            val moviesAdapter: MoviesAdapter? =
                recyclerView.adapter as MoviesAdapter?
            if (moviesAdapter != null && items != null) {
                moviesAdapter.setMoviesList(items)
            }
        }

        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(imageView: ImageView, url: String) {
            val options: RequestOptions = RequestOptions()
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_no_image)
            Glide.with(imageView.context).load(IMAGE_BASE_URL + url).apply(options).into(imageView)
        }
    }
}