package com.example.worldstarhiphop.artists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.worldstarhiphop.network.DeezerAPI
import com.example.worldstarhiphop.network.track.ArtistTrack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ArtistGridItemViewModel(artistId: Int) : ViewModel() {

    enum class DeezerApiStatus { LOADING, ERROR, DONE }

    private val _status = MutableLiveData<DeezerApiStatus>()

    val status: LiveData<DeezerApiStatus>
        get() = _status

    private val _tracks= MutableLiveData<List<ArtistTrack>>()

    val tracks: LiveData<List<ArtistTrack>>
        get() = _tracks


    // [H8 - Exercise 9]
    private var viewModelJob= Job()
    private val coroutineScope= CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getTracksVanArtiest(artistId)
    }

    // Is public zodat we kunnen refreshen
    fun getTracksVanArtiest(id: Int){

        coroutineScope.launch{
            val getPropertiesDeferred = DeezerAPI.retrofitService.getTracksVanArtiest(id)
            try{
                _status.value = DeezerApiStatus.LOADING
                val resultaat = getPropertiesDeferred.await()
                _tracks.value = resultaat.data
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