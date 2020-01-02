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
    fun insert(track: Track)

    @Query("SELECT * from artist_track_table WHERE primary_key = :key")
    suspend fun get(key: Int): Track?

    @Query("SELECT * FROM artist_track_table ORDER BY primary_key DESC")
    suspend fun getAllTracks(): List<Track>?

    @Query("DELETE FROM artist_track_table WHERE primary_key = :key")
    fun remove(key: Int)
}