package com.android.vamachallenge.data.network.model

import com.android.vamachallenge.data.db.model.GenreDB
import com.google.gson.annotations.SerializedName

data class GenreApiResponse(
    @SerializedName("genreId") val genreId: String,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
) {
    fun toGenreDB(): GenreDB {
        val genre = GenreDB()
        genre.genreId = this.genreId
        genre.name = this.name
        genre.url = this.url
        return genre
    }
}