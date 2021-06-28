package com.tabi.tmdbexplorer.util

object MovieSearchUtil {
    private val allMoviesNames = listOf("Losers", "Friends", "fasfafdaf", "lsafafasfd")

    /**
     * This method check search query is empty
     * if is empty then return true
     */
    fun isSearchEmpty(query: String): Boolean {
        return query.isEmpty()
    }

    /**
     * this method  search movies from list and return
     * if @param query is empty then return all movies list
     */
    fun searchMovieList(query: String): List<String> {
        val movie: ArrayList<String> = ArrayList()
        if(isSearchEmpty(query)){
            return allMoviesNames
        }
        allMoviesNames.forEach {
            if (it.startsWith(query, true)) {
                movie.add(it)
            }
        }
        return movie
    }

}