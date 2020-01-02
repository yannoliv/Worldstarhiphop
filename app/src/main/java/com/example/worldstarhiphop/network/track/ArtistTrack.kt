package com.example.worldstarhiphop.network.track

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.worldstarhiphop.network.album.Album

@Entity(tableName="artist_track_table")
data class ArtistTrack(
    @PrimaryKey
    @ColumnInfo(name = "primary_key")
    override var id: Int,
    @ColumnInfo(name = "readable")
    override var readable:Boolean,
    @ColumnInfo(name = "title")
    override var title: String,
    @ColumnInfo(name = "title_short")
    override var title_short: String,
    @ColumnInfo(name = "title_version")
    override var title_version:String,
    @ColumnInfo(name = "link")
    override var link: String,
    @ColumnInfo(name = "duration")
    override var duration: Int,
    @ColumnInfo(name = "rank")
    override var rank: Int,
    @ColumnInfo(name = "explicit_lyrics")
    override var explicit_lyrics: Boolean,
    @ColumnInfo(name = "explicit_content_lyrics")
    override var explicit_content_lyrics: Int,
    @ColumnInfo(name = "explicit_content_cover")
    override var explicit_content_cover: Int,
    @ColumnInfo(name = "preview")
    override var preview: String,
    @Ignore
    var contributors: List<Contributor>? = emptyList(),
    @Ignore
    var artist: TrackArtist? = TrackArtist(0,"",""),
    @Ignore
    override var album: Album = Album(0,"","","","","","","",""),
    @ColumnInfo(name = "type")
    override var type: String
): Track
{
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false

    constructor(): this(0,false,"","","","",0,
        0,false,0,0,"",null,
        null,Album(0,"","","","","","","",""),""
        )
}
