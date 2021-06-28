package com.tabi.tmdbexplorer.ui.search.adapter

import androidx.lifecycle.MutableLiveData
import com.tabi.tmdbexplorer.data.remote.models.Movie

class MovieViewModel(val movie: Movie) {
    var title: MutableLiveData<String> = MutableLiveData(movie.title)
    var year: MutableLiveData<String> = MutableLiveData(movie.releaseDate)
    var overview: MutableLiveData<String> = MutableLiveData(movie.overview)
    var posterPath: MutableLiveData<String> = MutableLiveData(movie.posterPath)
}