package com.android.vamachallenge.domain.model

data class Album(
    val name: String,
    val artistName: String,
    val thumbnail: String,
    val genres: List<String>,
    val releaseDate: String,
    val copyright: String,
    val url: String
)