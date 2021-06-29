package com.tabi.tmdbexplorer.ui.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.tabi.tmdbexplorer.R
import com.tabi.tmdbexplorer.databinding.ActivityMovieSearchBinding
import com.tabi.tmdbexplorer.ui.detail.MovieDetailActivity
import com.tabi.tmdbexplorer.ui.search.adapter.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieSearchActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MovieSearchViewModel

    @Inject
    lateinit var adapter: MoviesAdapter
    lateinit var binding: ActivityMovieSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_search)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.rvMovies.adapter = adapter

        startActivity(Intent(this, MovieDetailActivity::class.java))
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.action_search)
            .actionView as SearchView
        searchView.setSearchableInfo(
            searchManager
                .getSearchableInfo(componentName)
        )
        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchMovies(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        return if (id == R.id.action_search) {
            true
        } else super.onOptionsItemSelected(item)
    }

}