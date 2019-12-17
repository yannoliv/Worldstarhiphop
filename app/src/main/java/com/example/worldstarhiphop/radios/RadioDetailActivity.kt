package com.example.worldstarhiphop.radios

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.worldstarhiphop.R
import com.example.worldstarhiphop.databinding.RadioDetailBinding
import com.example.worldstarhiphop.network.radio.Radio
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RadioDetailActivity : AppCompatActivity() {

    private lateinit var binding: RadioDetailBinding
    private val viewModel: RadioViewModel by lazy {
        ViewModelProviders.of(this).get(RadioViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.radio_detail
        )

        // Radio instellen
        val gson = GsonBuilder().create()
       val radio: Radio = gson.fromJson(getIntent().extras!!.getString("geklikte_radio"), Radio::class.java)

        GlobalScope.launch {
            radio.tracks = viewModel.getTracksFromRadio(radio.id)
            binding.radio = radio
        }


        //binding.radio = viewModel.radios.value!!.filter { r -> r.id == radioId}.firstOrNull()
        //binding.radio = getIntent().getExtras().getString("geklikte_radio");

    }
}