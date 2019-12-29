package com.example.worldstarhiphop.network.radio

import com.example.worldstarhiphop.network.track.ArtistTrack

data class Radio(
    val id: Int,
    val title: String,
    val picture: String,
    val picture_small: String,
    val picture_medium: String,
    val picture_big: String,
    val picture_xl: String,
    val tracklist: String,
    val type: String,
    var artistTracks: List<ArtistTrack>?
)