package com.android.vamachallenge.data.repository.album

import com.android.vamachallenge.data.network.model.AlbumApiResponse
import com.android.vamachallenge.models.Resource

interface AlbumRepository {

    suspend fun getAlbums(): Resource<List<AlbumApiResponse>>
}