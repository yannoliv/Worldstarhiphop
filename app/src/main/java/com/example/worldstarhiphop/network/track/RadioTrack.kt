package com.example.worldstarhiphop.network.track

import com.example.worldstarhiphop.network.album.Album
import com.example.worldstarhiphop.network.artist.RadioArtist

data class RadioTrack(
     val id: Int,
     val readable:Boolean,
     val title: String,
     val title_short: String,
     val title_version:String,
     val link: String,
     val duration: Int,
     var rank: Int,
     val explicit_lyrics: Boolean,
     val explicit_content_lyrics: Int,
     val explicit_content_cover: Int,
     val preview: String,
    val artist: RadioArtist,
     val album: Album,
     val type: String
) {

}