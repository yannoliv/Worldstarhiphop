package com.example.worldstarhiphop.radios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.worldstarhiphop.network.DeezerAPI
import com.example.worldstarhiphop.network.radio.Radio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RadioViewModel : ViewModel() {

    enum class DeezerApiStatus { LOADING, ERROR, DONE }

    private val _status = MutableLiveData<DeezerApiStatus>()

    val status: LiveData<DeezerApiStatus>
        get() = _status

    private var _radios = MutableLiveData<List<Radio>>()

    val radios: LiveData<List<Radio>>
        get() = _radios


    // [H8 - Exercise 9]
    private var viewModelJob= Job()
    private val coroutineScope= CoroutineScope(viewModelJob + Dispatchers.Main)

    init{
        getRadios()
    }

    // Is public zodat we kunnen refreshen
    fun getRadios(){

        coroutineScope.launch{
            val getPropertiesDeferred = DeezerAPI.retrofitService.getRadios()
            try{
                _status.value =
                    DeezerApiStatus.LOADING
                val resultaat = getPropertiesDeferred.await()
                _radios.value = resultaat.data
                _status.value =
                    DeezerApiStatus.DONE
            }catch (t: Throwable){
                _status.value =
                    DeezerApiStatus.ERROR
                _radios.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
