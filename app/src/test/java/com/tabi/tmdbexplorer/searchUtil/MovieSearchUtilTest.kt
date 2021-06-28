package com.tabi.tmdbexplorer.searchUtil

import com.tabi.tmdbexplorer.util.MovieSearchUtil
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MovieSearchUtilTest {

    @Test
    fun `is search field empty`(){
        val isSearchEmpty = MovieSearchUtil.isSearchEmpty("")
        assertThat(isSearchEmpty).isTrue()
    }

    @Test
    fun `search movie list is empty`(){
        val movieList = MovieSearchUtil.searchMovieList(" ")
        assertThat(movieList).isEmpty()
    }

    @Test
    fun `search movie list is not empty`(){
        val movieList = MovieSearchUtil.searchMovieList("L")
        assertThat(movieList).isNotEmpty()
    }

    @Test
    fun `search movie list with empty string`(){
        val movieList = MovieSearchUtil.searchMovieList("")
        assertThat(movieList).isEqualTo(listOf("Losers", "Friends", "fasfafdaf", "lsafafasfd"))
    }

}