package com.example.worldstarhiphop

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.worldstarhiphop.network.Artist
import com.example.worldstarhiphop.ui.main.ArtiestenViewModel
import com.example.worldstarhiphop.ui.main.PhotoGridAdapter
import com.example.worldstarhiphop.ui.main.PlaylistsFragment
import kotlin.coroutines.coroutineContext

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

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Artist>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("deezerApiStatus")
fun bindStatus(statusImageView: ImageView, status: ArtiestenViewModel.DeezerApiStatus?) {
    when (status) {
        ArtiestenViewModel.DeezerApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ArtiestenViewModel.DeezerApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ArtiestenViewModel.DeezerApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

// 20. Exercise H7
class ArtiestListener(val clickListener: (artistId: Int) -> Unit) {
    fun onClick(artist: Artist) = clickListener(artist.id)
}