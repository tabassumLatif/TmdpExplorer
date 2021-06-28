package com.tabi.tmdbexplorer.ui.detail

import androidx.lifecycle.ViewModel
import com.tabi.tmdbexplorer.repository.ITmdbRepository
import javax.inject.Inject

class MovieDetailViewModel @Inject
constructor(private val repository: ITmdbRepository) : ViewModel() {
}