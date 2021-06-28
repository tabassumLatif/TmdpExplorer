package com.tabi.tmdbexplorer.repository

import androidx.lifecycle.MutableLiveData
import com.tabi.tmdbexplorer.data.remote.models.Movie
import com.tabi.tmdbexplorer.data.remote.models.MoviesResponse
import com.tabi.tmdbexplorer.util.Resource

class FakeTmdbRepository : ITmdbRepository {

    private var shouldReturnNetworkError = false
    private val movies = mutableListOf<Movie>()
    private val observableMovies = MutableLiveData<List<Movie>>(movies)

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun searchMovies(query: String, page: Int): Resource<MoviesResponse> {
        return if (shouldReturnNetworkError || query.isEmpty()) {
            if (query.isEmpty()) {
                Resource.error("query must be provided", null)
            } else {
                Resource.error("Network error", null)
            }
        } else {
            Resource.success(MoviesResponse(listOf(), 0, listOf(), 200, "", true, 1, 1))
        }
        refreshLiveData()
    }

    private fun refreshLiveData() {
        observableMovies.postValue(movies)
    }
}