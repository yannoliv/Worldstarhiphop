package com.example.worldstarhiphop.network

data class TrackListObject(
    val data: List<Track>,
    val total: Int,
    val next: String
)