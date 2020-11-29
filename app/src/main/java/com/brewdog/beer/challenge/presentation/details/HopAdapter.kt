package com.brewdog.beer.challenge.presentation.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.brewdog.beer.challenge.data.model.Hop
import com.brewdog.beer.challenge.databinding.ItemHopBinding

class HopAdapter:ListAdapter<Hop, HopViewHolder>(DIFF_CALLBACK_HOP) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        HopViewHolder.from(parent)

    override fun onBindViewHolder(holder: HopViewHolder, position: Int) =
        holder.onBind(getItem(position))
}

class HopViewHolder(
   private val binding: ItemHopBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(model: Hop) {
        binding.item = model
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): HopViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemHopBinding.inflate(layoutInflater, parent, false)
            return HopViewHolder(binding)
        }
    }
}

var DIFF_CALLBACK_HOP: DiffUtil.ItemCallback<Hop> =
    object : DiffUtil.ItemCallback<Hop>() {

        override fun areItemsTheSame(oldItem: Hop, newItem: Hop) =
            oldItem.name == newItem.name &&
                    oldItem.amount.unit == newItem.amount.unit &&
                    oldItem.amount.value == newItem.amount.value

        override fun areContentsTheSame(oldItem: Hop, newItem: Hop) =
            oldItem == newItem
    }
