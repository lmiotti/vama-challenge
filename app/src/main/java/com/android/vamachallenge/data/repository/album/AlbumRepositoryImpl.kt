package com.android.vamachallenge.data.repository.album

import com.android.vamachallenge.data.network.datasource.AlbumRemoteDataSource
import com.android.vamachallenge.data.network.model.AlbumApiResponse
import com.android.vamachallenge.models.Resource
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val remoteDataSource: AlbumRemoteDataSource
): AlbumRepository {
    override suspend fun getAlbums(): Resource<List<AlbumApiResponse>> {
        return remoteDataSource.getAlbums()
    }
}