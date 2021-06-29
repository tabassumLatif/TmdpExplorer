package com.tabi.tmdbexplorer.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tabi.tmdbexplorer.data.remote.models.MovieDetail
import com.tabi.tmdbexplorer.repository.ITmdbRepository
import com.tabi.tmdbexplorer.util.Event
import com.tabi.tmdbexplorer.util.Resource
import com.tabi.tmdbexplorer.util.Status
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject
constructor(private val repository: ITmdbRepository) : ViewModel() {

    private val _movies = MutableLiveData<Event<Resource<MovieDetail>>>()
    private val movies: LiveData<Event<Resource<MovieDetail>>> = _movies


    fun getMovieDetail(movieId: String = "380712") {

        viewModelScope.launch {
            _movies.value = Event(Resource.loading(null))
            viewModelScope.launch {
                val response = repository.getMovieDetail(movieId)
                _movies.value = Event(response)
                when (movies.value?.getContentIfNotHandled()?.status) {
                    Status.SUCCESS -> {
                        Log.d("tabi_detail", "getMovieDetail: " + response.data?.title)
//                        moviesList.postValue(response.data?.results)
                    }
                    Status.ERROR -> {
                        Log.d("tabi_detail", "getMovieDetail: ERROR")
                    }
                    Status.LOADING -> {
                        Log.d("tabi_detail", "getMovieDetail: LOADING")
                    }
                }

            }
        }
    }

}