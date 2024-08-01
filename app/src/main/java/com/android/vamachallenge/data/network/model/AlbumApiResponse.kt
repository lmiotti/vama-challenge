package com.android.vamachallenge.data.network.model

import com.android.vamachallenge.domain.model.Album
import com.google.gson.annotations.SerializedName

data class AlbumApiResponse(
    @SerializedName("artistName") val artistName: String,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("releaseDate") val releaseDate: String,
    @SerializedName("kind") val albums: String,
    @SerializedName("artistId") val artistId: String? = "",
    @SerializedName("artistUrl") val artistUrl: String? = "",
    @SerializedName("artworkUrl100") val artworkUrl: String,
    @SerializedName("genres") val genres: List<GenreApiResponse>,
    @SerializedName("url") val url: String,
    val copyright: String = ""
) {
    fun toAlbum(): Album =
        Album(
            name = this.name,
            artistName = this.artistName,
            thumbnail = this.artworkUrl,
            genres = this.genres.map { it.name },
            releaseDate = this.releaseDate,
            copyright = this.copyright,
            url = this.url
        )
}