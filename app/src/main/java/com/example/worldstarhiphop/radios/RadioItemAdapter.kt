package com.example.worldstarhiphop.radios

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.worldstarhiphop.databinding.RadioGridItemBinding
import com.example.worldstarhiphop.network.radio.Radio


class RadioItemAdapter : ListAdapter<Radio, RadioItemAdapter.RadioViewHolder>(
    DiffCallback
) {


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
        holder.bind(radio)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Radio>() {
        override fun areItemsTheSame(oldItem: Radio, newItem: Radio): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Radio, newItem: Radio): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class RadioViewHolder(private var binding: RadioGridItemBinding, viewType:Int):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(Radio: Radio) {
            binding.radio = Radio


            binding.executePendingBindings()
        }
    }
}