package com.android.vamachallenge.data.network.datasource

import com.android.vamachallenge.data.network.model.AlbumApiResponse
import com.android.vamachallenge.data.network.service.AlbumService
import com.android.vamachallenge.domain.model.Resource
import com.android.vamachallenge.data.network.utils.NetworkUtils
import javax.inject.Inject

class AlbumRemoteDataSourceImpl @Inject constructor(
    private val service: AlbumService
): AlbumRemoteDataSource {
    override suspend fun getAlbums(): Resource<List<AlbumApiResponse>> {
        val response = NetworkUtils.safeApiCall { service.getMostViewedAlbums() }
        return if (response is Resource.Success) {
            val feed = response.data?.feed
            val albums = feed?.results?.map {
                it.copy(copyright = feed.copyright)
            } ?: listOf()
            Resource.Success(data = albums)
        } else {
            Resource.Failure(networkError = response.error!!)
        }
    }
}