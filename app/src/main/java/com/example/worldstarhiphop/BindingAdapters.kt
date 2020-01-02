package com.example.worldstarhiphop

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.worldstarhiphop.network.artist.Artist
import com.example.worldstarhiphop.artists.ArtistViewModel
import com.example.worldstarhiphop.artists.TrackItemAdapter
import com.example.worldstarhiphop.artists.ArtistItemAdapter
import com.example.worldstarhiphop.network.radio.Radio
import com.example.worldstarhiphop.network.track.Track
import com.example.worldstarhiphop.radios.RadioItemAdapter
import com.example.worldstarhiphop.radios.RadiosViewModel

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?){
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

// Artists
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Artist>?) {
    val adapter = recyclerView.adapter as ArtistItemAdapter
    adapter.submitList(data)
}

@BindingAdapter("listTrackData")
fun bindRecyclerViewTrack(recyclerView: RecyclerView, data: List<Track>?){
    val adapter = recyclerView.adapter as TrackItemAdapter
    adapter.submitList(data)
}

@BindingAdapter("deezerApiStatus")
fun bindStatus(statusImageView: ImageView, status: ArtistViewModel.DeezerApiStatus?) {
    when (status) {
        ArtistViewModel.DeezerApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ArtistViewModel.DeezerApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ArtistViewModel.DeezerApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}


// Radios
@BindingAdapter("listDataRadio")
fun bindRadioRecyclerView(recyclerView: RecyclerView, data: List<Radio>?) {
    val adapter = recyclerView.adapter as RadioItemAdapter
    adapter.submitList(data)
}

@BindingAdapter("deezerApiStatusRadio")
fun bindRadioStatus(statusImageView: ImageView, status: RadiosViewModel.DeezerApiStatus?) {
    when (status) {
        RadiosViewModel.DeezerApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        RadiosViewModel.DeezerApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        RadiosViewModel.DeezerApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}