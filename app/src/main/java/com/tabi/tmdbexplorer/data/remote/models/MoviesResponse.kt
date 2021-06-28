package com.tabi.tmdbexplorer.data.remote.models

data class MoviesResponse(
    val errors: List<String>,
    val page: Int,
    val results: List<Movie>,
   val statusCode: Int,
     val statusMessage: String,
    val success: Boolean,
    val totalPages: Int,
    val totalResults: Int
)