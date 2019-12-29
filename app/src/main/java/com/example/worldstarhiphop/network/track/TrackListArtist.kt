package com.example.worldstarhiphop.network.track

data class TrackListArtist(
    val data: List<ArtistTrack>,
    val total: Int,
    val next: String
)