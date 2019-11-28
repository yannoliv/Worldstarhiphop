package com.example.worldstarhiphop.ui.main

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.worldstarhiphop.databinding.ArtiestenGridItemBinding
import com.example.worldstarhiphop.network.Artist
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity





class PhotoGridAdapter : ListAdapter<Artist, PhotoGridAdapter.ArtistViewHolder>(DiffCallback) {

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
    ): PhotoGridAdapter.ArtistViewHolder {
        return ArtistViewHolder(ArtiestenGridItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhotoGridAdapter.ArtistViewHolder, position: Int) {
        val artist = getItem(position)
        holder.bind(artist)
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
        fun bind(artist: Artist) {
            binding.artist = artist
            binding.recyclerLiedjeItem.adapter = ArtistGridItemAdapter()
            //binding.viewModel = ViewModelProviders.of(Activity as AppCompatActivity)
            //    .get(ArtistGridItemViewModel::class.java!!)
            binding.executePendingBindings()
        }
    }

}
