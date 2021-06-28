package com.tabi.tmdbexplorer.repository

import com.tabi.tmdbexplorer.data.remote.models.MovieDetail
import com.tabi.tmdbexplorer.data.remote.models.MoviesResponse
import com.tabi.tmdbexplorer.util.Resource

interface ITmdbRepository {
    suspend fun searchMovies(query : String, page : Int = 1) : Resource<MoviesResponse>
    suspend fun getMovieDetail(movieId : String) : Resource<MovieDetail>
}