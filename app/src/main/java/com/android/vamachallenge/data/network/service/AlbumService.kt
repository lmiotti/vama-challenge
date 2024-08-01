package com.android.vamachallenge.data.network.service

import com.android.vamachallenge.data.network.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface AlbumService {

    @GET("api/v2/us/music/most-played/100/albums.json")
    suspend fun getMostViewedAlbums(): Response<ApiResponse>
}