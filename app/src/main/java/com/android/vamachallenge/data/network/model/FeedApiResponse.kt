package com.android.vamachallenge.data.network.model

import com.google.gson.annotations.SerializedName

data class FeedApiResponse(
    @SerializedName("title") val title: String,
    @SerializedName("id") val id: String,
    @SerializedName("author") val author: AuthorFeedApiResponse,
    @SerializedName("links") val links: List<FeedLinksApiResponse>,
    @SerializedName("copyright") val copyright: String,
    @SerializedName("country") val country: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("updated") val updated: String,
    @SerializedName("results") val results: List<AlbumApiResponse>
)

data class AuthorFeedApiResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class FeedLinksApiResponse(
    @SerializedName("links") val links: String
)