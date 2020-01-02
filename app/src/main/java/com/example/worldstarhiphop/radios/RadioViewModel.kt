package com.example.worldstarhiphop.radios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.worldstarhiphop.network.DeezerAPI
import com.example.worldstarhiphop.network.radio.Radio
import com.example.worldstarhiphop.network.track.RadioTrack
import com.example.worldstarhiphop.network.track.Track
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RadioViewModel(radioId: Int) : ViewModel() {

    enum class DeezerApiStatus { LOADING, ERROR, DONE }

    // TRACKS van een bepaalde radio
    private val _statusTracksVanRadio = MutableLiveData<DeezerApiStatus>()

    val statusTracksVanRadio: LiveData<DeezerApiStatus>
        get() = _statusTracksVanRadio

    private var _tracksVanRadio = MutableLiveData<List<Track>>()

    val tracksVanRadio: LiveData<List<Track>>
        get() = _tracksVanRadio


    // [H8 - Exercise 9]
    private var viewModelJob= Job()
    private val coroutineScope= CoroutineScope(viewModelJob + Dispatchers.Main)

    init{
        getTracksFromRadio(radioId)
    }

    private fun getTracksFromRadio(radioId: Int){

        coroutineScope.launch{
            val getPropertiesDeferred = DeezerAPI.retrofitService.getTracksVanRadio(radioId)
            try{
                _statusTracksVanRadio.value = DeezerApiStatus.LOADING
                val resultaat = getPropertiesDeferred.await()
                val unformattedTracks = resultaat.data
                var rank: Int = 0
                _tracksVanRadio.value = unformattedTracks.map {
                    rank += 1
                    Track(it.id, it.title, it.link, it.duration, rank, it.preview)
                }
                _statusTracksVanRadio.value = DeezerApiStatus.DONE
            }catch (t: Throwable){
                _statusTracksVanRadio.value = DeezerApiStatus.ERROR
                _tracksVanRadio.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
