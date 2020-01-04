package com.example.worldstarhiphop.radios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.dinuscxj.refresh.RecyclerRefreshLayout
import com.example.worldstarhiphop.R
import com.example.worldstarhiphop.databinding.RadiosFragmentBinding

class RadioFragment : Fragment() {

    companion object {
        fun newInstance() = RadioFragment()
    }

    private lateinit var binding: RadiosFragmentBinding
    private val viewModel: RadiosViewModel by lazy {
        ViewModelProviders.of(this).get(RadiosViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.radios_fragment, container, false)

        // variabelen van ArtiestenFragment
        binding.listRadios.adapter = RadioItemAdapter(this)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initialiseerSwipeRefreshLayout()

        return binding.root
    }

    private fun initialiseerSwipeRefreshLayout() {
        val mSwipeRefreshLayout = binding.swipeContainer
        mSwipeRefreshLayout.isNestedScrollingEnabled = true

        mSwipeRefreshLayout.setOnRefreshListener(RecyclerRefreshLayout.OnRefreshListener {
            viewModel.getRadios()
            mSwipeRefreshLayout.setRefreshing(false)
        })
    }
}
