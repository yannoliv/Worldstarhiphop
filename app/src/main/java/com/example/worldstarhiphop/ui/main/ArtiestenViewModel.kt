package com.example.worldstarhiphop.ui.main

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


    private val _status = MutableLiveData<String>()

    val status: LiveData<String>
        get() = _status

    private val _artist = MutableLiveData<Artist>()

    val artist: LiveData<Artist>
        get() = _artist



    private var viewModelJob= Job()
    private val coroutineScope= CoroutineScope(viewModelJob + Dispatchers.Main)

    init{
        getPostMaloneProperties()
    }

    private fun getPostMaloneProperties(){

        coroutineScope.launch{
            var getPropertiesDeferred = DeezerAPI.retrofitService.getProperties()
            try{
                var resultaat = getPropertiesDeferred.await()
                _artist.value = resultaat
                _status.value =  "Success: ${resultaat?.name} "
            }catch (t: Throwable){
                _status.value = "Failure: " + t.message
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
