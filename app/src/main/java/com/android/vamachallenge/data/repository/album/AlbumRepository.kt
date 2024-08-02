package com.android.vamachallenge.data.repository.album

import com.android.vamachallenge.data.db.model.AlbumDB
import com.android.vamachallenge.data.network.model.AlbumApiResponse
import com.android.vamachallenge.models.Resource
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {

    suspend fun getAlbums(): Flow<Resource<List<AlbumDB>>>
}