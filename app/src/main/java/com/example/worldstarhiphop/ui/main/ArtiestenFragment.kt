package com.example.worldstarhiphop.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.worldstarhiphop.databinding.ArtiestenFragmentBinding
import com.example.worldstarhiphop.databinding.ArtiestenGridItemBinding
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.worldstarhiphop.R
import com.example.worldstarhiphop.databinding.ArtiestenLiedjeItemBinding


class ArtiestenFragment : Fragment() {

    private lateinit var binding: ArtiestenFragmentBinding
    private lateinit var bindingGridItem: ArtiestenGridItemBinding
    private lateinit var bindingLiedjeItem: ArtiestenLiedjeItemBinding


    private val viewModel: ArtiestenViewModel by lazy {
        ViewModelProviders.of(this).get(ArtiestenViewModel::class.java)
    }

    private val viewModelArtistGridItem: ArtistGridItemViewModel by lazy {
        ViewModelProviders.of(this).get(ArtistGridItemViewModel::class.java)
    }

    companion object {
        fun newInstance() = ArtiestenFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.artiesten_fragment, container, false)
        bindingGridItem = DataBindingUtil.inflate(inflater, R.layout.artiesten_grid_item, container, false)
        bindingLiedjeItem = DataBindingUtil.inflate(inflater, R.layout.artiesten_liedje_item, container, false)

        binding.listArtiesten.adapter = ArtistItemAdapter(this)
        bindingGridItem.recyclerLiedjeItem.adapter = TrackItemAdapter()

        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel
        bindingGridItem.viewModel = viewModelArtistGridItem

        initialiseerSwipeRefreshLayout()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    fun initialiseerSwipeRefreshLayout(){
        var mSwipeRefreshLayout = binding.swipeContainer
        mSwipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener{
            // Artiesten updaten somehow
            viewModel.getArtiesten()
            mSwipeRefreshLayout.isRefreshing = false
        })
    }

}
