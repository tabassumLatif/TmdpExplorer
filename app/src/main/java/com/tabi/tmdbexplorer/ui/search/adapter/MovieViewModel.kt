package com.tabi.tmdbexplorer.ui.search.adapter

import androidx.lifecycle.MutableLiveData
import com.tabi.tmdbexplorer.data.remote.models.Movie
import java.text.SimpleDateFormat
import java.util.*

class MovieViewModel(val movie: Movie) {
    var title: MutableLiveData<String> = MutableLiveData(movie.title)
    var year: MutableLiveData<String> = MutableLiveData(getYear(movie.releaseDate))
    var overview: MutableLiveData<String> = MutableLiveData(movie.overview)
    var posterPath: MutableLiveData<String> = MutableLiveData(movie.posterPath ?: "")
    var sameYear: MutableLiveData<Boolean> = MutableLiveData(isSameYear(year.value ?: ""))

    private fun getYear(date: String): String {
        val dateFormat_yyyyMMdd = SimpleDateFormat(
            "yyyy-MM-dd", Locale.ROOT
        )
        val formattedDate = dateFormat_yyyyMMdd.parse(date)
        val calendar = Calendar.getInstance()
        calendar.time = formattedDate!!
        return calendar.get(Calendar.YEAR).toString()
    }

    private fun isSameYear(year: String): Boolean{
        Calendar.getInstance().clear()
        val calendar = Calendar.getInstance()
        var currentYear = calendar.get(Calendar.YEAR)
        return year == currentYear.toString()
    }
}