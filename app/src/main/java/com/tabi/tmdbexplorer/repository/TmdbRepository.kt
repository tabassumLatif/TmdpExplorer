package com.tabi.tmdbexplorer.repository

import android.util.Log
import com.tabi.tmdbexplorer.data.remote.api.TmdbAPI
import com.tabi.tmdbexplorer.data.remote.models.MoviesResponse
import com.tabi.tmdbexplorer.util.Resource
import java.lang.Exception
import javax.inject.Inject

class TmdbRepository @Inject constructor(
    private val tmdbApi: TmdbAPI
) : ITmdbRepository {
    override suspend fun searchMovies(query: String, page: Int): Resource<MoviesResponse> {
        return try {
            val response = tmdbApi.searchMovies(query)
            Log.d("tabi_repo", "searchMovies: ")
            if(response.isSuccessful) {
                Log.d("tabi_repo", "searchMovies:isSuccessful ")
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)
            } else {
                Log.d("tabi_repo", "searchMovies:error ")
                when(response.body()?.statusCode){
                    300->{
                        Resource.error("An unknown error occured", null)
                    }400->{
                    Resource.error("An unknown error occured", null)
                    }500->{
                    Resource.error("An unknown error occured", null)
                    }
                    else -> {
                        Resource.error("An unknown error occured", null)
                    }
                }
            }
        } catch(e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }
}