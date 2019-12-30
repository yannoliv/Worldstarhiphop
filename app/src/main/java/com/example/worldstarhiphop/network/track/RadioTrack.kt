package com.example.worldstarhiphop.network.track

import com.example.worldstarhiphop.network.album.Album
import com.example.worldstarhiphop.network.artist.RadioArtist

data class RadioTrack(
    override val id: Int,
    override val readable:Boolean,
    override val title: String,
    override val title_short: String,
    override val title_version:String,
    override val link: String,
    override val duration: Int,
    override var rank: Int,
    override val explicit_lyrics: Boolean,
    override val explicit_content_lyrics: Int,
    override val explicit_content_cover: Int,
    override val preview: String,
    val artist: RadioArtist,
    override val album: Album,
    override val type: String
) :  Track{

}