package com.example.worldstarhiphop.ui.main

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.media.AudioAttributes
import android.media.MediaPlayer
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

            // Liedje afspelen
            binding.liedjeBalk.setOnClickListener(View.OnClickListener {
                initialiseerLiedje(track, mediaPlayer)
                startPlaying(artiestenFragment, mediaPlayer)
            })

            binding.executePendingBindings()
        }

        private fun startPlaying(artiestenFragment: ArtiestenFragment, mediaPlayer: MediaPlayer){
            if(mediaPlayer.isPlaying){
                mediaPlayer.pause()
                binding.liedjeBalk.setBackgroundColor(Color.WHITE)
                binding.aantalKijkers.setTextColor(Color.DKGRAY)
                binding.nummer.setTextColor(Color.DKGRAY)
                binding.titel.setTextColor(Color.DKGRAY)
                binding.imageViewPlayPause.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp)
                binding.imageViewPlayPause.backgroundTintList = ContextCompat.getColorStateList(artiestenFragment.context!!, R.color.gray)
            } else{
                mediaPlayer.start()
                binding.liedjeBalk.setBackgroundColor(Color.parseColor("#991a1a"))
                binding.aantalKijkers.setTextColor(Color.WHITE)
                binding.nummer.setTextColor(Color.WHITE)
                binding.titel.setTextColor(Color.WHITE)
                binding.imageViewPlayPause.setBackgroundResource(R.drawable.ic_pause_black_24dp)
                binding.imageViewPlayPause.backgroundTintList = ContextCompat.getColorStateList(artiestenFragment.context!!, R.color.white)
            }
        }


        private fun initialiseerLiedje(track: Track, mediaPlayer: MediaPlayer){
            try {
                if(mediaPlayer.isPlaying){
                    mediaPlayer.reset()
                }
                mediaPlayer.setDataSource(track.preview)
                mediaPlayer.prepare()
            } catch (e: Exception) {
                print(e)
            }
        }

    }

}