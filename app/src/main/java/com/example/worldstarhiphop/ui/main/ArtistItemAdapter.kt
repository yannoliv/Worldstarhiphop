package com.example.worldstarhiphop.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.worldstarhiphop.databinding.ArtiestenGridItemBinding
import com.example.worldstarhiphop.network.Artist
import androidx.lifecycle.ViewModelProviders


class ArtistItemAdapter(artiestenFragmentInput: ArtiestenFragment) : ListAdapter<Artist, ArtistItemAdapter.ArtistViewHolder>(DiffCallback) {

    var artiestenFragment = artiestenFragmentInput

    class PhotoViewHolder(internal val binding: ArtiestenGridItemBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.getRoot()) {
        private var viewModel: ArtistGridItemViewModel? = null

        fun setViewModel(viewModel: ArtistGridItemViewModel) {
            this.viewModel = viewModel
            binding.setViewModel(viewModel)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArtistItemAdapter.ArtistViewHolder {
        return ArtistViewHolder(ArtiestenGridItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ArtistItemAdapter.ArtistViewHolder, position: Int) {
        val artist = getItem(position)
        holder.bind(artist, artiestenFragment)
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
        fun bind(artist: Artist, artiestenFragment: ArtiestenFragment) {
            binding.artist = artist
            binding.recyclerLiedjeItem.adapter = TrackItemAdapter()
            ViewModelProviders.of(artiestenFragment)
                .get(ArtistGridItemViewModel::class.java!!).getTracksVanArtiest(artist.id)
            binding.viewModel = ViewModelProviders.of(artiestenFragment)
                .get(ArtistGridItemViewModel::class.java!!)
            binding.executePendingBindings()
        }
    }

}
