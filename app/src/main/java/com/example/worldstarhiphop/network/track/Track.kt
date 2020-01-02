package com.example.worldstarhiphop.network.track

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="artist_track_table")
data class Track(
    @PrimaryKey
    @ColumnInfo(name = "primary_key")
    var id: Int,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "link")
    var link: String,
    @ColumnInfo(name = "duration")
    var duration: Int,
    @ColumnInfo(name = "rank")
    var rank: Int,
    @ColumnInfo(name = "preview")
    var preview: String
){
    constructor(): this(0,"","",0,0,"")
}