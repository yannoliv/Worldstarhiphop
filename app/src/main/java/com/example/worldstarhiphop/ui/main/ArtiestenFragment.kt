package com.example.worldstarhiphop.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.worldstarhiphop.R

class ArtiestenFragment : Fragment() {

    companion object {
        fun newInstance() = ArtiestenFragment()
    }

    private lateinit var viewModel: ArtiestenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.artiesten_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ArtiestenViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
