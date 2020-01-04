package com.example.worldstarhiphop.radios

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.worldstarhiphop.R
import com.example.worldstarhiphop.databinding.RadioGridItemBinding
import com.example.worldstarhiphop.network.radio.Radio
import com.google.gson.Gson

class RadioItemAdapter(radioFragmentInput: RadioFragment) : ListAdapter<Radio, RadioItemAdapter.RadioViewHolder>(
    DiffCallback
) {

    val radioFragment: RadioFragment = radioFragmentInput

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RadioViewHolder {
        return RadioViewHolder(
            RadioGridItemBinding.inflate(LayoutInflater.from(parent.context)),
            viewType
        )
    }

    override fun onBindViewHolder(holder: RadioViewHolder, position: Int) {
        val radio = getItem(position)
        holder.bind(radio, radioFragment)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Radio>() {
        override fun areItemsTheSame(oldItem: Radio, newItem: Radio): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Radio, newItem: Radio): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class RadioViewHolder(private var binding: RadioGridItemBinding, viewType: Int) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(radio: Radio, radioFragment: RadioFragment) {
            binding.radio = radio

            binding.radioImage.setOnClickListener(View.OnClickListener {

                // Parse radio naar JSON
                val gson = Gson()
                val jsonRadio = gson.toJson(radio)

                // het object radio parsen naar een json
                val intent = Intent(radioFragment.activity, RadioDetailActivity::class.java)
                    .putExtra("geklikte_radio", jsonRadio)
                var options: ActivityOptions? = null

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // animation werkt enkel boven Lollipop
                    // Hier kan je ook meerdere animations voor maken
                    options = ActivityOptions.makeSceneTransitionAnimation(
                        radioFragment.activity,
                        binding.radioImage,
                        radioFragment.context!!.getString(R.string.radio_image_transition)
                    )
                    startActivity(radioFragment.context!!, intent, options.toBundle())
                } else {
                    startActivity(radioFragment.context!!, intent, null)
                }
            })

            binding.executePendingBindings()
        }
    }
}
