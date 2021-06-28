package com.tabi.tmdbexplorer.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.tabi.tmdbexplorer.repository.FakeTmdbRepository
import com.tabi.tmdbexplorer.ui.search.MovieSearchViewModel
import com.tabi.tmdbexplorer.util.Status
import com.tabi.tmdbexplorer.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class MoviesSearchViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private var viewModel: MovieSearchViewModel = MovieSearchViewModel(FakeTmdbRepository())

    @Before
    fun setup() {
        viewModel = MovieSearchViewModel(FakeTmdbRepository())
    }


    @Test
    fun `get movies result success`() {
        viewModel.searchMovies("test")
        val value = viewModel.movies
        assertThat(value.value?.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `return error movies result if query empty`() {
        viewModel.searchMovies("")
        val value = viewModel.movies
        assertThat(value.value?.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }
}