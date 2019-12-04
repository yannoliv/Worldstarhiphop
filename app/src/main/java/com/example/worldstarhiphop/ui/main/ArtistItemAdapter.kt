package com.example.worldstarhiphop.ui.main

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.worldstarhiphop.databinding.ArtiestenGridItemBinding
import com.example.worldstarhiphop.network.Artist
import androidx.lifecycle.ViewModelProviders
import com.example.worldstarhiphop.R


class ArtistItemAdapter(
    artiestenFragmentInput: ArtiestenFragment,
    mediaPlayerInput: MediaPlayer
) : ListAdapter<Artist, ArtistItemAdapter.ArtistViewHolder>(DiffCallback) {

    var artiestenFragment = artiestenFragmentInput
    var mediaPlayer= mediaPlayerInput

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArtistViewHolder {
        return ArtistViewHolder(ArtiestenGridItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val artist = getItem(position)
        holder.bind(artist, artiestenFragment, mediaPlayer)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Artist>() {
        override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class ArtistViewHolder(private var binding: ArtiestenGridItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(artist: Artist, artiestenFragment: ArtiestenFragment, mediaPlayer: MediaPlayer) {
            binding.artist = artist

            binding.recyclerLiedjeItem.adapter = TrackItemAdapter(artiestenFragment, mediaPlayer)

            ViewModelProviders.of(artiestenFragment)
                .get(ArtistGridItemViewModel::class.java).getTracksVanArtiest(artist.id)
            binding.viewModel = ViewModelProviders.of(artiestenFragment)
                .get(ArtistGridItemViewModel::class.java)

            // onclick listener voor de liedjes te openen.

            binding.whitebarArtist.setOnClickListener(View.OnClickListener {
                if(binding.recyclerLiedjeItem.visibility == View.GONE){
                    binding.pijltje.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp)
                    binding.streepBovenRecyclerView.visibility = View.VISIBLE
                    binding.recyclerLiedjeItem.visibility = View.VISIBLE
                } else {
                    binding.pijltje.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp)
                    binding.streepBovenRecyclerView.visibility = View.GONE
                    binding.recyclerLiedjeItem.visibility = View.GONE
                }
            })

            binding.executePendingBindings()
        }
    }
}