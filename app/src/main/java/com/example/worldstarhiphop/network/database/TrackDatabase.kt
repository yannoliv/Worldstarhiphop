package com.example.worldstarhiphop.network.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.worldstarhiphop.network.track.Track

@Database(entities = [Track::class], version = 1, exportSchema = false)
abstract class TrackDatabase : RoomDatabase() {
    abstract val trackDatabaseDao: TrackDatabaseDao
    companion object {
        // Via instance voorkom je meerdere connections
        // Volatile zorgt ervoor dat instance altijd up to date is
        @Volatile
        private var INSTANCE: TrackDatabase? = null

        fun getInstance(context: Context): TrackDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TrackDatabase::class.java,
                        "track_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

// Meer info over room database
// https://codelabs.developers.google.com/codelabs/kotlin-android-training-room-database/index.html?index=..%2F..android-kotlin-fundamentals#5
