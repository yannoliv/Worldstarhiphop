package com.example.worldstarhiphop

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.worldstarhiphop.network.album.Album
import com.example.worldstarhiphop.network.artist.Artist
import com.example.worldstarhiphop.network.database.TrackDatabase
import com.example.worldstarhiphop.network.database.TrackDatabaseDao
import com.example.worldstarhiphop.network.track.ArtistTrack
import com.example.worldstarhiphop.network.track.Contributor
import com.example.worldstarhiphop.network.track.Track
import com.example.worldstarhiphop.network.track.TrackArtist
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class TrackDatabaseTest{

    private lateinit var trackDatabaseDao: TrackDatabaseDao
    private lateinit var db: TrackDatabase

    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, TrackDatabase::class.java)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build()
        trackDatabaseDao = db.trackDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }

    @Test
   @Throws(Exception::class)
    fun insertAndGetTrack() {
        // Track maken
        val mockContributors: List<Contributor> = emptyList()
        val artist: TrackArtist = TrackArtist(10,"yann","xxx")
        val album: Album = Album(10,"titel","cover","xxx",
            "xxx","xxx","xxx","tracklist","Album")
        val track = ArtistTrack(200,true,"ff","fff","fff",
            "",200,200,true,20,20,
            "",mockContributors,artist,album,"")

        // Track testen
        trackDatabaseDao.insert(track)
        val testTrack = trackDatabaseDao.get(200)
        assertEquals(testTrack!!.title, "ff")
    }

}



// Geïnspireerd door
// https://github.com/google-developer-training/android-kotlin-fundamentals-starter-apps/blob/master/TrackMySleepQuality-Starter/app/src/androidTest/java/com/example/android/trackmysleepquality/SleepDatabaseTest.kt