package com.tabi.tmdbexplorer.ui.search.adapter

import androidx.lifecycle.MutableLiveData
import com.tabi.tmdbexplorer.data.remote.models.Movie
import java.text.SimpleDateFormat
import java.util.*

class MovieViewModel(val movie: Movie) {
    var onItemClick: (() -> Unit)? = null
    var title: MutableLiveData<String> = MutableLiveData(movie.title)
    var year: MutableLiveData<String> = MutableLiveData(getYear(movie.releaseDate))
    var overview: MutableLiveData<String> = MutableLiveData(movie.overview)
    var posterPath: MutableLiveData<String> = MutableLiveData(movie.posterPath ?: "")
    var sameYear: MutableLiveData<Boolean> = MutableLiveData(isSameYear(year.value ?: ""))

    private fun getYear(date: String): String {
        if (date.isNullOrEmpty()) {
            return ""
        }
        val dateFormatYyyyMMdd = SimpleDateFormat(
            "yyyy-MM-dd", Locale.ROOT
        )
        val formattedDate = dateFormatYyyyMMdd.parse(date)
        val calendar = Calendar.getInstance()
        calendar.time = formattedDate!!
        return calendar.get(Calendar.YEAR).toString()
    }

    private fun isSameYear(year: String): Boolean {
        Calendar.getInstance().clear()
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        return year == currentYear.toString()
    }

    fun onClick() {
        onItemClick?.invoke()
    }
}