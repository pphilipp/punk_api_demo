package com.brewdog.beer.challenge.presentation.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.brewdog.beer.challenge.data.model.Malt
import com.brewdog.beer.challenge.databinding.ItemMaltBinding

class MaltAdapter : ListAdapter<Malt, MaltViewHolder>(DIFF_CALLBACK_MALT) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MaltViewHolder.from(parent)

    override fun onBindViewHolder(holder: MaltViewHolder, position: Int) =
        holder.onBind(getItem(position))
}

class MaltViewHolder(
    private val binding: ItemMaltBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(model: Malt) {
        binding.item = model
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): MaltViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemMaltBinding.inflate(layoutInflater, parent, false)
            return MaltViewHolder(binding)
        }
    }
}

var DIFF_CALLBACK_MALT: DiffUtil.ItemCallback<Malt> =
    object : DiffUtil.ItemCallback<Malt>() {

        override fun areItemsTheSame(oldItem: Malt, newItem: Malt) =
            oldItem.name == newItem.name &&
                    oldItem.amount.unit == newItem.amount.unit &&
                    oldItem.amount.value == newItem.amount.value

        override fun areContentsTheSame(oldItem: Malt, newItem: Malt) =
            oldItem == newItem
    }
