package com.example.worldstarhiphop.artiesten

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.worldstarhiphop.databinding.ArtiestenGridItemBinding
import com.example.worldstarhiphop.network.artist.Artist
import androidx.lifecycle.ViewModelProviders
import com.example.worldstarhiphop.R
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator


class ArtistItemAdapter(
    artistFragmentInput: ArtistFragment,
    mediaPlayerInput: MediaPlayer
) : ListAdapter<Artist, ArtistItemAdapter.ArtistViewHolder>(
    DiffCallback
) {

    var artiestenFragment = artistFragmentInput
    var mediaPlayer= mediaPlayerInput

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArtistViewHolder {
        return ArtistViewHolder(
            ArtiestenGridItemBinding.inflate(LayoutInflater.from(parent.context)),
            viewType
        )
    }

    val DURATION:Long = 0
    var on_attach = true;

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

    class ArtistViewHolder(private var binding: ArtiestenGridItemBinding, viewType:Int):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(artist: Artist, artistFragment: ArtistFragment, mediaPlayer: MediaPlayer) {
            binding.artist = artist

            binding.recyclerLiedjeItem.adapter =
                TrackItemAdapter(
                    artistFragment,
                    mediaPlayer
                )

            ViewModelProviders.of(artistFragment)
                .get(ArtistGridItemViewModel::class.java).getTracksVanArtiest(artist.id)
            binding.viewModel = ViewModelProviders.of(artistFragment)
                .get(ArtistGridItemViewModel::class.java)

            // onclick listener voor de liedjes te openen.

            binding.whitebarArtist.setOnClickListener(View.OnClickListener {

                if(binding.recyclerLiedjeItem.visibility == View.GONE){
                    var rotateHalf = AnimationUtils.loadAnimation(
                        artistFragment.context,
                        R.anim.semi_clockwise_rotation
                    )
                    rotateHalf.setInterpolator(LinearInterpolator())
                    rotateHalf.setFillAfter(true)
                    binding.pijltje.startAnimation(rotateHalf)

                    // Visible maken
                    binding.streepBovenRecyclerView.visibility = View.VISIBLE
                    binding.recyclerLiedjeItem.visibility = View.VISIBLE

                } else {
                    var rotateHalf = AnimationUtils.loadAnimation(
                        artistFragment.context,
                        R.anim.second_semi_clockwise_rotation
                    )
                    rotateHalf.setInterpolator(LinearInterpolator())
                    rotateHalf.setFillAfter(true)
                    binding.pijltje.startAnimation(rotateHalf)

                    // Visible uit
                    binding.streepBovenRecyclerView.visibility = View.GONE
                    binding.recyclerLiedjeItem.visibility = View.GONE
                }
            })

            binding.executePendingBindings()
        }
    }
}