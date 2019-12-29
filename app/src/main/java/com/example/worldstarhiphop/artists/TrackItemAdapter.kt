package com.example.worldstarhiphop.artists

import android.graphics.Color
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.worldstarhiphop.R
import com.example.worldstarhiphop.databinding.TrackItemBinding
import com.example.worldstarhiphop.network.track.Track


class TrackItemAdapter(
    mediaPlayerInput: MediaPlayer
) : ListAdapter<Track, TrackItemAdapter.TrackViewHolder>(
    DiffCallback
) {

    private var mediaPlayer = mediaPlayerInput

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrackViewHolder {
        return TrackViewHolder(
            TrackItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val track = getItem(position)
        holder.bind(track, mediaPlayer)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class TrackViewHolder(private var binding: TrackItemBinding):
        RecyclerView.ViewHolder(binding.root) {


        fun bind(track: Track, mediaPlayer: MediaPlayer) {
            binding.track = track


            // Klik op de pauze knop van het liedje
            binding.imageViewPlayPause.setOnClickListener(View.OnClickListener {
                if(mediaPlayer.isPlaying){
                    unfocusTrack()
                    mediaPlayer.pause()
                } else{
                    focusTrack()
                    mediaPlayer.start()
                }
            })

            // Klik op de balk van het liedje
            binding.liedjeBalk.setOnClickListener(View.OnClickListener {
                if(!mediaPlayer.isPlaying)
                {
                    initialiseerLiedje(track,mediaPlayer)
                    focusTrack()
                    mediaPlayer.start()
                }
            })

            binding.executePendingBindings()
        }

        private fun focusTrack() {
            binding.liedjeBalk.setBackgroundColor(Color.parseColor("#991a1a"))
            binding.aantalKijkers.setTextColor(Color.WHITE)
            binding.nummer.setTextColor(Color.WHITE)
            binding.titel.setTextColor(Color.WHITE)
            binding.imageViewPlayPause.setBackgroundResource(R.drawable.ic_pause_black_24dp)
            binding.imageViewPlayPause.setColorFilter(Color.parseColor("#FFFFFF"))
            binding.imageViewPlayPauseCircle.setColorFilter(Color.parseColor("#FFFFFF"))
        }

        private fun unfocusTrack(){
            binding.liedjeBalk.setBackgroundColor(Color.WHITE)
            binding.aantalKijkers.setTextColor(Color.DKGRAY)
            binding.nummer.setTextColor(Color.DKGRAY)
            binding.titel.setTextColor(Color.DKGRAY)
            binding.imageViewPlayPause.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp)
            binding.imageViewPlayPause.setColorFilter(Color.parseColor("#aaaaaa"))
            binding.imageViewPlayPauseCircle.setColorFilter(Color.parseColor("#aaaaaa"))
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