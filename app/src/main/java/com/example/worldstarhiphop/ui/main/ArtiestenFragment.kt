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

class ArtiestenFragment : Fragment() {

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
        val binding = ArtiestenFragmentBinding.inflate(inflater)

        binding.listArtiesten.adapter = PhotoGridAdapter()


        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
