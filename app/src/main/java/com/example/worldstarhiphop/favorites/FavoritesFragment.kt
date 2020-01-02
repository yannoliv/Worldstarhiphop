package com.example.worldstarhiphop.favorites

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.media.MediaPlayer
import android.util.Log
import com.example.worldstarhiphop.artists.TrackItemAdapter
import com.example.worldstarhiphop.databinding.FavoritesFragmentBinding
import com.example.worldstarhiphop.databinding.FavoritesFragmentBinding.inflate
import com.example.worldstarhiphop.network.database.TrackDatabase


class FavoritesFragment : Fragment() {

    private var mediaPlayer: MediaPlayer = MediaPlayer()
    private lateinit var binding: FavoritesFragmentBinding

    companion object {
        fun newInstance() =
            FavoritesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val application = requireNotNull(this.activity).application
        val dataSource = TrackDatabase.getInstance(application).trackDatabaseDao
        val viewModelFactory =
            FavoriteTracksViewModelFactory(
                dataSource,
                application
            )
        val favoriteTracksViewModel = ViewModelProviders.of(this,
            viewModelFactory).get(FavoriteTracksViewModel::class.java)

        binding.lifecycleOwner = this

        binding.favoriteTracksViewModel = favoriteTracksViewModel

        binding.recyclerLiedjeItem.adapter = TrackItemAdapter(mediaPlayer,this.activity!!)

    }

}