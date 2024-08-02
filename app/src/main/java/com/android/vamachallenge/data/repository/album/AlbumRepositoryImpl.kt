package com.android.vamachallenge.data.repository.album

import com.android.vamachallenge.data.db.datasource.AlbumLocalDataSource
import com.android.vamachallenge.data.db.model.AlbumDB
import com.android.vamachallenge.data.network.datasource.AlbumRemoteDataSource
import com.android.vamachallenge.domain.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val remoteDataSource: AlbumRemoteDataSource,
    private val localDataSource: AlbumLocalDataSource
): AlbumRepository {
    override suspend fun getAlbums(): Flow<Resource<List<AlbumDB>>> = flow {
        val localAlbums = localDataSource.getAlbums()
        if (localAlbums.isEmpty()) {
            emit(Resource.Loading())
        } else {
            emit(Resource.Success(localAlbums))
        }
        val remoteAlbumsResource = remoteDataSource.getAlbums()
        if (remoteAlbumsResource is Resource.Success) {
            localDataSource.saveAlbums(remoteAlbumsResource.data ?: listOf())
            emit(Resource.Success(localDataSource.getAlbums()))
        } else {
            emit(Resource.Failure(remoteAlbumsResource.error!!))
        }
    }
}