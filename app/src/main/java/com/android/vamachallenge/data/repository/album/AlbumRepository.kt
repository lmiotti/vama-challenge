package com.android.vamachallenge.data.repository.album

import com.android.vamachallenge.data.db.model.AlbumDB
import com.android.vamachallenge.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {

    suspend fun getAlbums(): Flow<Resource<List<AlbumDB>>>
}