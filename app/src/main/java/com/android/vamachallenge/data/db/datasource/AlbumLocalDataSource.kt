package com.android.vamachallenge.data.db.datasource

import com.android.vamachallenge.data.db.model.AlbumDB
import com.android.vamachallenge.data.network.model.AlbumApiResponse

interface AlbumLocalDataSource {
    fun getAlbums(): List<AlbumDB>
    fun saveAlbums(albums: List<AlbumApiResponse>)
}