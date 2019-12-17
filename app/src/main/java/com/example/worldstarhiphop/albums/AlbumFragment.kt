package com.example.worldstarhiphop.albums

import android.media.AudioAttributes
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.media.MediaPlayer
import android.widget.TextView
import androidx.transition.Explode
import androidx.transition.Fade
import androidx.transition.TransitionManager
import com.example.worldstarhiphop.databinding.PlaylistsFragmentBinding
import com.example.worldstarhiphop.databinding.PlaylistsFragmentBinding.inflate
import com.bumptech.glide.Glide
import com.example.worldstarhiphop.R


class AlbumFragment : Fragment() {


    private var muziekurl: String = "https://cdns-preview-d.dzcdn.net/stream/c-df36f056f3f9770ab7b7b466e32975fd-5.mp3"

    private var mediaPlayer: MediaPlayer = MediaPlayer()
    private lateinit var binding: PlaylistsFragmentBinding

    // URL's
    private val albumCover: String = "https://cdns-images.dzcdn.net/images/cover/4642b8e3e0a89f92a6e2bfed13d8f31c/500x500-000000-80-0-0.jpg"
    private val albumCoverSmall: String = "https://cdns-images.dzcdn.net/images/cover/4642b8e3e0a89f92a6e2bfed13d8f31c/56x56-000000-80-0-0.jpg"

    companion object {
        fun newInstance() =
            AlbumFragment()
    }

    private lateinit var viewModel: AlbumViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(AlbumViewModel::class.java)
        initialiseerAlbum()

        binding.btnPlay.setOnClickListener {
            startPlaying()
        }

    }

    private fun initialiseerAlbum() {
        Glide.with(this)
            .load(albumCover)
            .thumbnail(
                Glide.with(this)
                    .load(albumCoverSmall)
            )
            .into(binding.albumCover);
        //mediaPlayer = MediaPlayer.create(context, com.example.worldstarhiphop.R.raw.postmalone)
        try {
            mediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                )
                setDataSource(muziekurl)
                prepare() // might take long! (for buffering, etc)
            }
        } catch (e: Exception) {
            print(e)
        }
    }

    private fun startPlaying(){
        if(mediaPlayer.isPlaying){
            mediaPlayer.pause()
            binding.btnPlay.text = "Hervat"
        } else{
            mediaPlayer.start()
            binding.btnPlay.text = "Pauzeer"
        }
    }

}



/*
try{
            mediaPlayer = MediaPlayer().apply {
                setAudioAttributes(AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build())
                setDataSource(muziekurl)
                prepare() // might take long! (for buffering, etc)
                start()
            }
        } catch (e: Exception){
            print(e)
        }

                    val labelText = TextView(context).apply {
            text = "Label"
            id = R.id.text
        }
            val explode: Explode = Explode()
            //TransitionManager.beginDelayedTransition(binding.albumLayout, explode)
            //binding.albumLayout.addView(labelText)

 */