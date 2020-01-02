package com.example.worldstarhiphop.network.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.worldstarhiphop.network.track.ArtistTrack
import com.example.worldstarhiphop.network.track.Track

@Dao
interface TrackDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(track: Track)

    @Query("SELECT * from artist_track_table WHERE primary_key = :key")
    fun get(key: Int): LiveData<Track?>

    @Query("SELECT * FROM artist_track_table ORDER BY primary_key DESC")
    fun getAllTracks(): LiveData<List<Track>>

    @Query("DELETE FROM artist_track_table WHERE primary_key = :key")
    fun remove(key: Int)

    /** Onderstaande methodes zijn voor te testen **/

    @Query("SELECT * FROM artist_track_table ORDER BY primary_key DESC")
    fun getAllTracksTesting(): List<Track>

    @Query("SELECT * from artist_track_table WHERE primary_key = :key")
    fun getTesting(key: Int): Track?
}