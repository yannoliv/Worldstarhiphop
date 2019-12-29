package com.example.worldstarhiphop.artists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory

class ArtistGridItemViewModelFactory(private val artistId: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArtistGridItemViewModel::class.java)) {
            return ArtistGridItemViewModel(artistId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}