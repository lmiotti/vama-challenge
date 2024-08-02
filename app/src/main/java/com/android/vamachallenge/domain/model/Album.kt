package com.android.vamachallenge.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class Album(
    val name: String,
    val artistName: String,
    val thumbnail: String,
    val genres: List<String> = listOf(),
    val releaseDate: String,
    val copyright: String,
    val url: String
)