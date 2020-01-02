package com.example.worldstarhiphop.artists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.worldstarhiphop.network.DeezerAPI
import com.example.worldstarhiphop.network.track.Track
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ArtistGridItemViewModel : ViewModel() {

    enum class DeezerApiStatus { LOADING, ERROR, DONE }

    private val _status = MutableLiveData<DeezerApiStatus>()

    val status: LiveData<DeezerApiStatus>
        get() = _status

    private val _tracks= MutableLiveData<List<Track>>()

    val tracks: LiveData<List<Track>>
        get() = _tracks


    // [H8 - Exercise 9]
    private var viewModelJob= Job()
    private val coroutineScope= CoroutineScope(viewModelJob + Dispatchers.Main)

    // Is public zodat we kunnen refreshen
    fun getTracksVanArtiest(id: Int){

        coroutineScope.launch{
            val getPropertiesDeferred = DeezerAPI.retrofitService.getTracksVanArtiest(id)
            try{
                _status.value = DeezerApiStatus.LOADING
                val resultaat = getPropertiesDeferred.await()
                val unformattedTracks = resultaat.data
                var rank: Int = 0
                _tracks.value = unformattedTracks.map {
                    rank += 1
                    Track(it.id, it.title, it.link, it.duration, rank, it.preview)
                }
                _status.value = DeezerApiStatus.DONE
            }catch (t: Throwable){
                _status.value = DeezerApiStatus.ERROR
                _tracks.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}