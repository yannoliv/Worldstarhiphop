package com.example.worldstarhiphop.network.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.worldstarhiphop.network.track.ArtistTrack
import com.example.worldstarhiphop.network.track.Track

@Dao
interface TrackDatabaseDao {
    @Insert
    fun insert(track: ArtistTrack)

    @Update
    fun update(track: ArtistTrack)

    @Query("SELECT * from artist_track_table WHERE primary_key = :key")
    fun get(key: Int): ArtistTrack?

    @Query("SELECT * FROM artist_track_table ORDER BY primary_key DESC")
    fun getAllTracks(): LiveData<List<ArtistTrack>>

    @Query("DELETE FROM artist_track_table")
    fun clear()
}