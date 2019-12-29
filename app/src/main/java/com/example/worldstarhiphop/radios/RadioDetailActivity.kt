package com.example.worldstarhiphop.radios

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.worldstarhiphop.R
import com.example.worldstarhiphop.artists.ArtistGridItemViewModel
import com.example.worldstarhiphop.artists.ArtistGridItemViewModelFactory
import com.example.worldstarhiphop.artists.TrackItemAdapter
import com.example.worldstarhiphop.databinding.RadioDetailBinding
import com.example.worldstarhiphop.network.radio.Radio
import com.example.worldstarhiphop.network.track.Track
import com.google.gson.GsonBuilder


class RadioDetailActivity() : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer = MediaPlayer()

    private lateinit var viewModelFactory: RadioViewModelFactory
    private lateinit var viewModel: RadioViewModel

    private lateinit var binding: RadioDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.radio_detail
        )

        // Radio instellen
        val gson = GsonBuilder().create()
        val radio: Radio = gson.fromJson(getIntent().extras!!.getString("geklikte_radio"), Radio::class.java)

        binding.listTracks.adapter = TrackItemAdapter(mediaPlayer)
        binding.radio = radio

        viewModelFactory = RadioViewModelFactory(radio.id)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(RadioViewModel::class.java)
        binding.viewModel = viewModel

        Handler().postDelayed(
            {
                // This method will be executed once the timer is over
                val adapter = binding.listTracks.adapter as TrackItemAdapter
                adapter.submitList(viewModel.tracksVanRadio.value)
                binding.listTracks.adapter = adapter
            },
            1000 // value in milliseconds
        )



        //binding.radio = viewModel.radios.value!!.filter { r -> r.id == radioId}.firstOrNull()
        //binding.radio = getIntent().getExtras().getString("geklikte_radio");

    }
}