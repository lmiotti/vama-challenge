package com.android.vamachallenge.domain.usecase

import com.android.vamachallenge.data.repository.album.AlbumRepository
import com.android.vamachallenge.di.DefaultDispatcher
import com.android.vamachallenge.di.IoDispatcher
import com.android.vamachallenge.domain.model.Album
import com.android.vamachallenge.domain.model.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAlbumListUseCase @Inject constructor(
    private val albumRepository: AlbumRepository,
    @IoDispatcher private val iOdispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): Flow<Resource<List<Album>>> {
        return albumRepository.getAlbums()
            .flowOn(iOdispatcher)
            .map {
                when(it) {
                    is Resource.Success -> {
                        val albums = it.data?.map { it.toAlbum() } ?: listOf()
                        Resource.Success(albums)
                    }
                    is Resource.Failure -> Resource.Failure(networkError = it.error!!)
                    is Resource.Loading -> Resource.Loading()
                }
            }
            .flowOn(defaultDispatcher)
    }
}