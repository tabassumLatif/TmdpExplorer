package com.tabi.tmdbexplorer.ui.search

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tabi.tmdbexplorer.data.remote.models.MoviesResponse
import com.tabi.tmdbexplorer.repository.ITmdbRepository
import com.tabi.tmdbexplorer.util.Event
import com.tabi.tmdbexplorer.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieSearchViewModel @Inject
constructor(private val repository: ITmdbRepository) : ViewModel() {

    private val _movies = MutableLiveData<Event<Resource<MoviesResponse>>>()
    val movies: LiveData<Event<Resource<MoviesResponse>>> = _movies


    fun searchMovies(query: String) {
        viewModelScope.launch {
            _movies.value = Event(Resource.loading(null))
            viewModelScope.launch {
                val response = repository.searchMovies(query)
                _movies.value = Event(response)
                Log.d("tabi_movie_result", "searchMovies: "+movies.value?.getContentIfNotHandled()?.data?.results?.size)
            }
        }
    }


}