package com.example.worldstarhiphop.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.worldstarhiphop.network.Artist
import com.example.worldstarhiphop.network.DeezerAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtiestenViewModel : ViewModel() {

    enum class DeezerApiStatus { LOADING, ERROR, DONE }

    private val _status = MutableLiveData<DeezerApiStatus>()

    val status: LiveData<DeezerApiStatus>
        get() = _status

    private val _artists = MutableLiveData<List<Artist>>()

    val artists: LiveData<List<Artist>>
        get() = _artists



    private var viewModelJob= Job()
    private val coroutineScope= CoroutineScope(viewModelJob + Dispatchers.Main)

    init{
        getArtiesten()
    }

    private fun getArtiesten(){

        coroutineScope.launch{
            var getPropertiesDeferred = DeezerAPI.retrofitService.getArtiesten()
            try{
                _status.value = DeezerApiStatus.LOADING
                var resultaat = getPropertiesDeferred.await()
                _artists.value = resultaat.data
                _status.value = DeezerApiStatus.DONE
            }catch (t: Throwable){
                _status.value = DeezerApiStatus.ERROR
                _artists.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
