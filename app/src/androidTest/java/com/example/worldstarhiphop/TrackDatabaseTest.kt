package com.example.worldstarhiphop

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.worldstarhiphop.network.database.TrackDatabase
import com.example.worldstarhiphop.network.database.TrackDatabaseDao
import com.example.worldstarhiphop.network.track.ArtistTrack
import com.example.worldstarhiphop.network.track.Track
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
    fun insertAndGetNight() {
        //val track = ArtistTrack()
        //trackDatabaseDao.insert(track)
        //val tracks = trackDatabaseDao.getAllTracks()
        //assertEquals(tracks?.value, -1)
    }

}



// Geïnspireerd door
// https://github.com/google-developer-training/android-kotlin-fundamentals-starter-apps/blob/master/TrackMySleepQuality-Starter/app/src/androidTest/java/com/example/android/trackmysleepquality/SleepDatabaseTest.kt