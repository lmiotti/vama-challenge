package com.android.vamachallenge.data.network.model

import com.google.gson.annotations.SerializedName

data class GenreApiResponse(
    @SerializedName("genreId") val genreId: String,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)