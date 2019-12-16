package com.example.worldstarhiphop.network.track

import com.example.worldstarhiphop.network.track.Track

data class TrackListObject(
    val data: List<Track>,
    val total: Int,
    val next: String
)