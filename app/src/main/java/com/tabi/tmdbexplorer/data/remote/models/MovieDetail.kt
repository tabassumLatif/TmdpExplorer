package com.tabi.tmdbexplorer.data.remote.models

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("belongs_to_collection") val belongsToCollection: String,
    @SerializedName("budget") val budget: Int,
    @SerializedName("genres") val genres: List<Genres>,
    @SerializedName("homepage") val homepage: String,
    @SerializedName("id") val id: Int,
    @SerializedName("imdb_id") val imdbId: String,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("production_companies") val productionCompanies: List<String>,
    @SerializedName("production_countries") val productionCountries: List<String>,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("revenue") val revenue: Int,
    @SerializedName("runtime") val runtime: Int,
    @SerializedName("spoken_languages") val spokenLanguages: List<String>,
    @SerializedName("status") val status: String,
    @SerializedName("tagline") val tagline: String,
    @SerializedName("title") val title: String,
    @SerializedName("video") val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Int,
    @SerializedName("vote_count") val voteCount: Int
)
