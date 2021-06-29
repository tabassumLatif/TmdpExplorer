package com.tabi.tmdbexplorer.ui.detail

import android.util.Log
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
    lateinit var movie: Movie


    fun getMovieDetail() {

        viewModelScope.launch {
            _movieDetail.value = Event(Resource.loading(null))
            viewModelScope.launch {
                val response = repository.getMovieDetail(movie.id.toString())
                _movieDetail.value = Event(response)
                when (moviesDetail.value?.getContentIfNotHandled()?.status) {
                    Status.SUCCESS -> {
                        Log.d("tabi_detail", "getMovieDetail: " + response.data?.title)
//                        moviesList.postValue(response.data?.results)
                    }
                    Status.ERROR -> {
                        Log.d(
                            "tabi_detail",
                            "getMovieDetail: ERROR " + moviesDetail.value?.getContentIfNotHandled()?.message
                        )
                    }
                    Status.LOADING -> {
                        Log.d("tabi_detail", "getMovieDetail: LOADING")
                    }
                }

            }
        }
    }

}