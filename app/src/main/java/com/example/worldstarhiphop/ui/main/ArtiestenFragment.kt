package com.example.worldstarhiphop.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.worldstarhiphop.R
import com.example.worldstarhiphop.databinding.ArtiestenFragmentBinding
import com.example.worldstarhiphop.databinding.ArtiestenGridItemBinding
import android.R.attr.colorPrimary
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.artiesten_fragment.view.*




class ArtiestenFragment : Fragment() {

    private lateinit var binding: ArtiestenFragmentBinding


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
        binding = ArtiestenFragmentBinding.inflate(inflater)

        binding.listArtiesten.adapter = PhotoGridAdapter()

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
        mSwipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener{
            // Artiesten updaten somehow
            viewModel.getArtiesten()
            mSwipeRefreshLayout.isRefreshing = false
        })
    }

}
