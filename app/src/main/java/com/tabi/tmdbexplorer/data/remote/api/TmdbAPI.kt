package com.tabi.tmdbexplorer.data.remote.api

import com.tabi.tmdbexplorer.BuildConfig
import com.tabi.tmdbexplorer.data.remote.models.MovieDetail
import com.tabi.tmdbexplorer.data.remote.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface TmdbAPI {

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") searchQuery: String,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): Response<MoviesResponse>

    @GET("movie/")
    suspend fun getMovieDetail(
        @Url url: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): Response<MovieDetail>
}