package com.example.worldstarhiphop.ui.main

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.worldstarhiphop.network.Artist
import com.example.worldstarhiphop.network.DeezerAPI
import com.example.worldstarhiphop.network.Track
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

    val artists: LiveData<List<Track>>
        get() = _tracks


    // [H8 - Exercise 9]
    private var viewModelJob= Job()
    private val coroutineScope= CoroutineScope(viewModelJob + Dispatchers.Main)


    init{
        getTracksVanArtiest()
    }

    // Is public zodat we kunnen refreshen
    public fun getTracksVanArtiest(){

        coroutineScope.launch{
            var getPropertiesDeferred = DeezerAPI.retrofitService.getTracksVanArtiest()
            try{
                _status.value = DeezerApiStatus.LOADING
                var resultaat = getPropertiesDeferred.await()
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