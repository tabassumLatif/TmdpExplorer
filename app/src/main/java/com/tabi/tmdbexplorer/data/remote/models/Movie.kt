package com.tabi.tmdbexplorer.data.remote.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Movie(
    @SerializedName("adult") val adult : Boolean,
    @SerializedName("backdrop_path") val backdrop_path : String,
    @SerializedName("genre_ids") val genre_ids : List<String>,
    @SerializedName("id") val id : Int,
    @SerializedName("original_language") val original_language : String,
    @SerializedName("original_title") val originalTitle : String,
    @SerializedName("overview") val overview : String,
    @SerializedName("popularity") val popularity : Double,
    @SerializedName("poster_path") val posterPath : String,
    @SerializedName("release_date") val releaseDate : String,
    @SerializedName("title") val title : String,
    @SerializedName("video") val video : Boolean,
    @SerializedName("vote_average") val voteAverage : Double,
    @SerializedName("vote_count") val voteCount : Int
) : Serializable