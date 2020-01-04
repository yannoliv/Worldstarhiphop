package com.example.worldstarhiphop.artists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.worldstarhiphop.network.DeezerAPI
import com.example.worldstarhiphop.network.artist.Artist
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ArtistViewModel : ViewModel() {

    enum class DeezerApiStatus { LOADING, ERROR, DONE }

    private val _status = MutableLiveData<DeezerApiStatus>()

    val status: LiveData<DeezerApiStatus>
        get() = _status

    private var _artists = MutableLiveData<List<Artist>>()

    val artists: LiveData<List<Artist>>
        get() = _artists

    // [H8 - Exercise 9]
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getArtists()
    }

    // Is public zodat we kunnen refreshen
    fun getArtists() {

        coroutineScope.launch {
            val getPropertiesDeferred = DeezerAPI.retrofitService.getArtiesten()
            try {
                _status.value =
                    DeezerApiStatus.LOADING
                val resultaat = getPropertiesDeferred.await()
                _artists.value = resultaat.data
                _status.value =
                    DeezerApiStatus.DONE
            } catch (t: Throwable) {
                _status.value =
                    DeezerApiStatus.ERROR
                _artists.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
