package com.android.vamachallenge.domain.usecase

import com.android.vamachallenge.data.repository.album.AlbumRepository
import com.android.vamachallenge.domain.model.Album
import com.android.vamachallenge.models.Resource
import javax.inject.Inject

class GetAlbumListUseCase @Inject constructor(
    private val albumRepository: AlbumRepository
) {

    suspend operator fun invoke(): Resource<List<Album>> {
        val response = albumRepository.getAlbums()
        return if (response is Resource.Success) {
            val albums = response.data?.map {
                it.toAlbum()
            } ?: listOf()
            return Resource.Success(albums)
        } else {
            Resource.Failure(networkError = response.error!!)
        }
    }
}