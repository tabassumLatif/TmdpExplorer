package com.tabi.tmdbexplorer.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tabi.tmdbexplorer.data.remote.models.Movie
import com.tabi.tmdbexplorer.data.remote.models.MovieDetail
import com.tabi.tmdbexplorer.repository.ITmdbRepository
import com.tabi.tmdbexplorer.util.Event
import com.tabi.tmdbexplorer.util.Resource
import com.tabi.tmdbexplorer.util.Status
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject
constructor(private val repository: ITmdbRepository) : ViewModel() {

    private val _movieDetail = MutableLiveData<Event<Resource<MovieDetail>>>()
    private val moviesDetail: LiveData<Event<Resource<MovieDetail>>> = _movieDetail

    var title: MutableLiveData<String> = MutableLiveData()
    var releaseDate: MutableLiveData<String> = MutableLiveData()
    var overview: MutableLiveData<String> = MutableLiveData()
    var duration: MutableLiveData<String> = MutableLiveData()
    var rating: MutableLiveData<String> = MutableLiveData()
    var averageRating: MutableLiveData<Float> = MutableLiveData()
    var revenue: MutableLiveData<String> = MutableLiveData()
    var posterPath: MutableLiveData<String> = MutableLiveData("")

    lateinit var movie: Movie

    fun getMovieDetail() {

        viewModelScope.launch {
            _movieDetail.value = Event(Resource.loading(null))
            viewModelScope.launch {
                val response = repository.getMovieDetail(movie.id.toString())
                _movieDetail.value = Event(response)
                when (moviesDetail.value?.getContentIfNotHandled()?.status) {
                    Status.SUCCESS -> {
                        duration.postValue("${response.data?.runtime ?: "0"} min")
                        revenue.postValue("$${response.data?.revenue ?: "0"}")
                        rating.postValue("${response.data?.voteCount?.toInt() ?: "0"} votes")
                        averageRating.postValue(response.data?.voteAverage?.toFloat())
                    }
                    Status.ERROR -> {

                    }
                    Status.LOADING -> {
                    }
                }

            }
        }
    }

    fun setMovieValues(movie: Movie) {
        this.movie = movie
        title.postValue(movie.title)
        releaseDate.postValue(movie.releaseDate)
        overview.postValue(movie.overview)
        posterPath.postValue(movie.posterPath ?: "")

    }
}