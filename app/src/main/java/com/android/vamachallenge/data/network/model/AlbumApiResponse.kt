package com.android.vamachallenge.data.network.model

import com.android.vamachallenge.data.db.model.AlbumDB
import com.google.gson.annotations.SerializedName
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmList

data class AlbumApiResponse(
    @SerializedName("artistName") val artistName: String,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("releaseDate") val releaseDate: String,
    @SerializedName("kind") val kind: String,
    @SerializedName("artistId") val artistId: String? = "",
    @SerializedName("artistUrl") val artistUrl: String? = "",
    @SerializedName("artworkUrl100") val artworkUrl: String,
    @SerializedName("genres") val genres: List<GenreApiResponse>,
    @SerializedName("url") val url: String,
    val copyright: String = ""
) {
    fun toAlbumDB(): AlbumDB {
        val album = AlbumDB()
        album.artistName = this.artistName
        album.id = this.id
        album.name = this.name
        album.releaseDate = this.releaseDate
        album.kind = this.kind
        album.artistId = this.artistId ?: ""
        album.artistUrl = this.artistUrl ?: ""
        album.artworkUrl = this.artworkUrl
        album.genres = this.genres.map { it.toGenreDB() }.toRealmList()
        album.url = this.url
        album.copyright = this.copyright
        return album
    }
}