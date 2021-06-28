package com.tabi.tmdbexplorer.ui

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

class MovieSearchViewModel @ViewModelInject
constructor(private val repository: ITmdbRepository) : ViewModel() {

    private val _movies = MutableLiveData<Event<Resource<MoviesResponse>>>()
    val movies: LiveData<Event<Resource<MoviesResponse>>> = _movies

    fun searchMovies(query: String) {
        viewModelScope.launch {
            _movies.value = Event(Resource.loading(null))
            viewModelScope.launch {
                val response = repository.searchMovies(query)
                _movies.value = Event(response)
            }
        }
    }


}