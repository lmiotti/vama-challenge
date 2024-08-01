package com.android.vamachallenge.data.network.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("feed") val feed: FeedApiResponse
)