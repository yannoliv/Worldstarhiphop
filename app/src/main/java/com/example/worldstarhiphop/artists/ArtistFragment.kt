package com.example.worldstarhiphop.artists

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.dinuscxj.refresh.RecyclerRefreshLayout
import com.example.worldstarhiphop.R
import com.example.worldstarhiphop.databinding.ArtistsFragmentBinding


class ArtistFragment : Fragment() {

    private lateinit var binding: ArtistsFragmentBinding
    private var mediaPlayer: MediaPlayer = MediaPlayer()
    private val viewModel: ArtistViewModel by lazy {
        ViewModelProviders.of(this).get(ArtistViewModel::class.java)
    }

    companion object {
        fun newInstance() =
            ArtistFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.artists_fragment, container, false)

        /** Beginnen met mediaplayer te initialiseren **/
        mediaPlayer.apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
        }

        // variabelen van ArtiestenFragment
        binding.listArtiesten.adapter = ArtistItemAdapter(this,mediaPlayer)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initialiseerSwipeRefreshLayout()

        return binding.root
    }

    private fun initialiseerSwipeRefreshLayout(){
        val mSwipeRefreshLayout = binding.swipeContainer
        mSwipeRefreshLayout.isNestedScrollingEnabled = true

        mSwipeRefreshLayout.setOnRefreshListener(RecyclerRefreshLayout.OnRefreshListener{
            viewModel.getArtists()
            mSwipeRefreshLayout.setRefreshing(false)
        })
    }

}
