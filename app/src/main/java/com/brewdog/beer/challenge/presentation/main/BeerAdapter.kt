package com.brewdog.beer.challenge.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.brewdog.beer.challenge.data.model.SimpleBeer
import com.brewdog.beer.challenge.databinding.ItemBeerBinding

class BeerAdapter(
    private val onItemClickListener: (SimpleBeer) -> Unit
) : ListAdapter<SimpleBeer, BeerViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder =
        BeerViewHolder.from(parent, onItemClickListener)

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        val model = getItem(position)
        holder.onBind(model)
    }
}

class BeerViewHolder(
    private val binding: ItemBeerBinding,
    private val onItemClickListener: (SimpleBeer) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(model: SimpleBeer) {
        binding.item = model
        binding.apply {
            with(model) {
                root.setOnClickListener { onItemClickListener(this) }
            }
        }
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup, onItemClickListener: (SimpleBeer) -> Unit): BeerViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemBeerBinding.inflate(layoutInflater, parent, false)
            return BeerViewHolder(binding, onItemClickListener)
        }
    }
}

var DIFF_CALLBACK: DiffUtil.ItemCallback<SimpleBeer> =
    object : DiffUtil.ItemCallback<SimpleBeer>() {

    override fun areItemsTheSame(oldItem: SimpleBeer, newItem: SimpleBeer): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SimpleBeer, newItem: SimpleBeer): Boolean =
        oldItem == newItem
}