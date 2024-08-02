package com.android.vamachallenge.data.network.datasource

import com.android.vamachallenge.data.network.model.AlbumApiResponse
import com.android.vamachallenge.domain.model.Resource

interface AlbumRemoteDataSource {

    suspend fun getAlbums(): Resource<List<AlbumApiResponse>>
}