package com.example.worldstarhiphop.network.track

import com.example.worldstarhiphop.network.album.Album

data class ArtistTrack(
    var id: Int,
    var readable: Boolean,
    var title: String,
    var title_short: String,
    var title_version: String,
    var link: String,
    var duration: Int,
    var rank: Int,
    var explicit_lyrics: Boolean,
    var explicit_content_lyrics: Int,
    var explicit_content_cover: Int,
    var preview: String,
    var contributors: List<Contributor>? = emptyList(),
    var artist: TrackArtist? = TrackArtist(0, "", ""),
    var album: Album = Album(0, "", "", "", "", "", "", "", ""),
    var type: String
) {
    constructor() : this(0, false, "", "", "", "", 0,
        0, false, 0, 0, "", null,
        null, Album(0, "", "", "", "", "", "", "", ""), ""
        )
}
