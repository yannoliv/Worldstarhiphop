package com.example.worldstarhiphop.network.track

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.worldstarhiphop.network.album.Album

@Entity(tableName="artist_track_table")
data class ArtistTrack(
    @PrimaryKey
    @ColumnInfo(name = "primary_key")
    override val id: Int,
    @ColumnInfo(name = "readable")
    override val readable:Boolean,
    @ColumnInfo(name = "title")
    override val title: String,
    @ColumnInfo(name = "title_short")
    override val title_short: String,
    @ColumnInfo(name = "title_version")
    override val title_version:String,
    @ColumnInfo(name = "link")
    override val link: String,
    @ColumnInfo(name = "duration")
    override val duration: Int,
    @ColumnInfo(name = "rank")
    override var rank: Int,
    @ColumnInfo(name = "explicit_lyrics")
    override val explicit_lyrics: Boolean,
    @ColumnInfo(name = "explicit_content_lyrics")
    override val explicit_content_lyrics: Int,
    @ColumnInfo(name = "explicit_content_cover")
    override val explicit_content_cover: Int,
    @ColumnInfo(name = "preview")
    override val preview: String,
    @ColumnInfo(name = "contributors")
    val contributors: List<Contributor>,
    @ColumnInfo(name = "artist")
    val artist: TrackArtist,
    @ColumnInfo(name = "album")
    override val album: Album,
    @ColumnInfo(name = "type")
    override val type: String): Track
{
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
}
