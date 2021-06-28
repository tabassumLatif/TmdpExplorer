package com.tabi.tmdbexplorer.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tabi.tmdbexplorer.data.remote.models.Movie
import com.tabi.tmdbexplorer.data.remote.models.MoviesResponse
import com.tabi.tmdbexplorer.repository.ITmdbRepository
import com.tabi.tmdbexplorer.util.Event
import com.tabi.tmdbexplorer.util.Resource
import com.tabi.tmdbexplorer.util.Status
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieSearchViewModel @Inject
constructor(private val repository: ITmdbRepository) : ViewModel() {

    private val _movies = MutableLiveData<Event<Resource<MoviesResponse>>>()
    val movies: LiveData<Event<Resource<MoviesResponse>>> = _movies
    val moviesList: MutableLiveData<List<Movie>> = MutableLiveData()
    val isMovieFound: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()


    fun searchMovies(query: String) {
        isLoading.postValue(true)
        isMovieFound.postValue(true)
        viewModelScope.launch {
            _movies.value = Event(Resource.loading(null))
            viewModelScope.launch {
                val response = repository.searchMovies(query)
                _movies.value = Event(response)
                isLoading.postValue(false)
                when (movies.value?.getContentIfNotHandled()?.status) {
                    Status.SUCCESS -> {
                        moviesList.postValue(response.data?.results)
                        isMovieFound.postValue(response.data?.results?.size!! > 0)
                    }
                    Status.ERROR -> {
                        isMovieFound.postValue(false)
                    }
                    Status.LOADING -> {
                        isMovieFound.postValue(false)
                    }
                }

            }
        }
    }


}