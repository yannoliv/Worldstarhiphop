package com.example.worldstarhiphop.favorites

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.worldstarhiphop.network.database.TrackDatabaseDao

class FavoriteTracksViewModelFactory(
    private val dataSource: TrackDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteTracksViewModel::class.java)) {
            return FavoriteTracksViewModel(
                dataSource,
                application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}