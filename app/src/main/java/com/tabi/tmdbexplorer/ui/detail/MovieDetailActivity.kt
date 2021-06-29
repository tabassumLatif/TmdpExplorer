package com.tabi.tmdbexplorer.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.tabi.tmdbexplorer.R
import com.tabi.tmdbexplorer.data.remote.models.Movie
import com.tabi.tmdbexplorer.databinding.ActivityMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MovieDetailViewModel

    lateinit var binding: ActivityMovieDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        intent.let {
            viewModel.movie = it.extras?.getSerializable("movie") as Movie
        }
        viewModel.getMovieDetail()
    }
}