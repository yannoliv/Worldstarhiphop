package com.example.worldstarhiphop.ui.main

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.worldstarhiphop.R
import com.example.worldstarhiphop.databinding.ArtiestenLiedjeItemBinding
import com.example.worldstarhiphop.network.Track
import android.widget.CompoundButton
import androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked
import androidx.databinding.adapters.TextViewBindingAdapter.setText


class TrackItemAdapter(
    artiestenFragmentInput: ArtiestenFragment,
    mediaPlayerInput: MediaPlayer
) : ListAdapter<Track, TrackItemAdapter.TrackViewHolder>(DiffCallback) {

    private val artiestenFragment = artiestenFragmentInput
    private var mediaPlayer = mediaPlayerInput

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrackItemAdapter.TrackViewHolder {
        return TrackViewHolder(ArtiestenLiedjeItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: TrackItemAdapter.TrackViewHolder, position: Int) {
        val track = getItem(position)
        holder.bind(track, artiestenFragment, mediaPlayer)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class TrackViewHolder(private var binding: ArtiestenLiedjeItemBinding):
        RecyclerView.ViewHolder(binding.root) {


        fun bind(track: Track, artiestenFragment: ArtiestenFragment, mediaPlayer: MediaPlayer) {
            binding.track = track


            // Klik op de pauze knop van het liedje
            binding.imageViewPlayPause.setOnClickListener(View.OnClickListener {
                if(mediaPlayer.isPlaying){
                    unfocusTrack(artiestenFragment)
                    mediaPlayer.pause()
                } else{
                    focusTrack(artiestenFragment)
                    mediaPlayer.start()
                }
            })

            // Klik op de balk van het liedje
            binding.liedjeBalk.setOnClickListener(View.OnClickListener {
                if(!mediaPlayer.isPlaying)
                {
                    initialiseerLiedje(track,mediaPlayer)
                    focusTrack(artiestenFragment)
                    mediaPlayer.start()
                }
            })

            binding.executePendingBindings()
        }

        private fun focusTrack(artiestenFragment: ArtiestenFragment) {
            binding.liedjeBalk.setBackgroundColor(Color.parseColor("#991a1a"))
            binding.aantalKijkers.setTextColor(Color.WHITE)
            binding.nummer.setTextColor(Color.WHITE)
            binding.titel.setTextColor(Color.WHITE)
            binding.imageViewPlayPause.setBackgroundResource(R.drawable.ic_pause_black_24dp)
            binding.imageViewPlayPause.backgroundTintList =
                ContextCompat.getColorStateList(artiestenFragment.context!!, R.color.white)
            binding.imageViewPlayPauseCircle.backgroundTintList =
                ContextCompat.getColorStateList(artiestenFragment.context!!, R.color.white)
        }

        private fun unfocusTrack(artiestenFragment: ArtiestenFragment){
            binding.liedjeBalk.setBackgroundColor(Color.WHITE)
            binding.aantalKijkers.setTextColor(Color.DKGRAY)
            binding.nummer.setTextColor(Color.DKGRAY)
            binding.titel.setTextColor(Color.DKGRAY)
            binding.imageViewPlayPause.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp)
            binding.imageViewPlayPause.backgroundTintList = ContextCompat.getColorStateList(artiestenFragment.context!!, R.color.gray)
            binding.imageViewPlayPauseCircle.backgroundTintList = ContextCompat.getColorStateList(artiestenFragment.context!!, R.color.gray)
        }


        private fun initialiseerLiedje(track: Track, mediaPlayer: MediaPlayer){
            try {
                mediaPlayer.reset()
                mediaPlayer.setVolume( 1.0f,1.0f)
                mediaPlayer.setDataSource(track.preview)
                mediaPlayer.prepare()
            } catch (e: Exception) {
                print(e)
            }
        }

    }

}

/*
if (mediaPlayer.isPlaying) {
                    // Als het liedje al aan het spelen is EN de song is actief
                    pausePlaying(artiestenFragment, mediaPlayer)
                } else if (!mediaPlayer.isPlaying && !newState) { // LAATST HIER YANNICK
                    // Er is niets aan het spelen maar de balk is wel actief
                    startPlaying(artiestenFragment, mediaPlayer)
                } else if (!mediaPlayer.isPlaying && newState) {
                    // BEGIN STATE
                    // Is NIET aan het spelen en is nieuwe state
                    initialiseerLiedje(track, mediaPlayer)
                    startPlaying(artiestenFragment, mediaPlayer)
                } else if (mediaPlayer.isPlaying && oudePositie != huidigePositie){
                    // Er is al een liedje aant spelen, en niet nieuw ->
                    Log.i("PAUZE", "mediaplayer isplaying en niet newstate")
                    pausePlaying(artiestenFragment, mediaPlayer)
                }
 */

/*

                // de balk is niet actief -> begin dit nieuw liedje
                /*
                if(oudePositie != huidigePositie){
                    initialiseerLiedje(track, mediaPlayer)
                    startPlaying(artiestenFragment, mediaPlayer)
                }
                 */
                // Als oudepositie == huidige positie wilt het zeggen dat je op hetzelfde
                // hebt geklikt -> doe niks
 */