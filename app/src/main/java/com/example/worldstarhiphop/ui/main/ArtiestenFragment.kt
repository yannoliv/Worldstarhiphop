package com.example.worldstarhiphop.ui.main

import android.graphics.Color
import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.worldstarhiphop.databinding.ArtiestenFragmentBinding
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dinuscxj.refresh.RecyclerRefreshLayout
import com.example.worldstarhiphop.R


class ArtiestenFragment : Fragment() {

    private lateinit var binding: ArtiestenFragmentBinding

    private var mediaPlayer: MediaPlayer = MediaPlayer()


    private val viewModel: ArtiestenViewModel by lazy {
        ViewModelProviders.of(this).get(ArtiestenViewModel::class.java)
    }

    companion object {
        fun newInstance() = ArtiestenFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.artiesten_fragment, container, false)

        mediaPlayer.apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
        }

        binding.listArtiesten.adapter = ArtistItemAdapter(this, mediaPlayer)

        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel

        initialiseerSwipeRefreshLayout()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    fun initialiseerSwipeRefreshLayout(){
        var mSwipeRefreshLayout = binding.swipeContainer
        mSwipeRefreshLayout.setNestedScrollingEnabled(true);

        mSwipeRefreshLayout.setOnRefreshListener(RecyclerRefreshLayout.OnRefreshListener{
            viewModel.getArtiesten()
            mSwipeRefreshLayout.setRefreshing(false)
        })
    }

}
