package com.example.worldstarhiphop.favorites

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.worldstarhiphop.network.artist.Artist
import com.example.worldstarhiphop.network.database.TrackDatabaseDao
import com.example.worldstarhiphop.network.track.ArtistTrack
import com.example.worldstarhiphop.network.track.Track
import kotlinx.coroutines.*

class FavoriteTracksViewModel (
    private val database: TrackDatabaseDao,
    application: Application): AndroidViewModel(application){

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var tracks = database.getAllTracks()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun insert(track:Track) {

        // Inserten in databank
        uiScope.launch {
            withContext(Dispatchers.IO) {
                //Als hij er al in zit, mag hij niet nog is toegevoegd worden ( SQL primary key stuff )
                //TODO controleren of hij er al in zit
                database.insert(track)
            }
        }
    }

    fun remove(track: Track) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                database.remove(track.id)
            }
        }
    }
}