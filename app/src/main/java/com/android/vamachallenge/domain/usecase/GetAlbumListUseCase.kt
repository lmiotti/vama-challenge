package com.android.vamachallenge.domain.usecase

import com.android.vamachallenge.data.repository.album.AlbumRepository
import com.android.vamachallenge.domain.model.Album
import com.android.vamachallenge.models.NetworkError
import com.android.vamachallenge.models.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAlbumListUseCase @Inject constructor(
    private val albumRepository: AlbumRepository
) {

    suspend operator fun invoke(): Flow<Resource<List<Album>>> {
        return albumRepository.getAlbums()
            .map {
                when(it) {
                    is Resource.Success -> {
                        val albums = it.data?.map { it.toAlbum() } ?: listOf()
                        Resource.Success(albums)
                    }
                    is Resource.Failure -> Resource.Failure(networkError = it.error!!)// ?: NetworkError(message = "Unexpected Error"))
                    is Resource.Loading -> Resource.Loading()
                }
            }
            .flowOn(Dispatchers.IO)
    }
}