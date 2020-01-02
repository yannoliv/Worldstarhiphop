package com.example.worldstarhiphop.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.worldstarhiphop.network.artist.Artist
import com.example.worldstarhiphop.network.database.TrackDatabaseDao
import com.example.worldstarhiphop.network.track.ArtistTrack
import com.example.worldstarhiphop.network.track.Track
import kotlinx.coroutines.*

class FavoriteTracksViewModel (
    val database: TrackDatabaseDao,
    application: Application): AndroidViewModel(application){

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private var _tracks = MutableLiveData<List<Track>>()

    val tracks: LiveData<List<Track>>
        get() = _tracks

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    init {
        getTracks()
    }

    fun insert(track:Track) {
        //Als hij er al in zit, mag hij niet nog is toegevoegd worden ( SQL primary key stuff )
        if(!tracks.value!!.contains(track)){

            // We voegen de track hier ook al toe zodat de livedata automatisch
            // wordt geupdate
            GlobalScope.launch(Dispatchers.Main) {
                val trackList: MutableList<Track> = mutableListOf()
                tracks.value!!.forEach {
                    trackList.add(it)
                }
                trackList.add(track)
                _tracks.value = trackList
            }

            // Inserten in databank
            uiScope.launch {
                withContext(Dispatchers.IO) {
                    database.insert(track)
                }
            }
        }
    }

    fun remove(track: Track) {
        GlobalScope.launch(Dispatchers.Main) {
            val trackList: MutableList<Track> = mutableListOf()
            tracks.value!!.forEach {
                trackList.add(it)
            }
            trackList.remove(track)
            _tracks.value = trackList
        }
        uiScope.launch {
            withContext(Dispatchers.IO) {
                database.remove(track.id)
            }
        }
    }

    fun getTracks(){
        GlobalScope.launch(Dispatchers.Main) {
            _tracks.value = database.getAllTracks()
        }
    }
}