package com.example.worldstarhiphop.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.worldstarhiphop.databinding.ArtiestenLiedjeItemBinding
import com.example.worldstarhiphop.network.Track

class TrackItemAdapter : ListAdapter<Track, TrackItemAdapter.TrackViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrackItemAdapter.TrackViewHolder {
        return TrackViewHolder(ArtiestenLiedjeItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: TrackItemAdapter.TrackViewHolder, position: Int) {
        val track = getItem(position)
        holder.bind(track)
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
        fun bind(track: Track) {
            binding.track = track
            binding.executePendingBindings()
        }
    }

}
