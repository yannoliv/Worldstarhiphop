package com.example.worldstarhiphop.radios

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory

class RadioViewModelFactory(private val radioId: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RadioViewModel::class.java)) {
            return RadioViewModel(radioId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}