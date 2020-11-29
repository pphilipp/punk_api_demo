package com.brewdog.beer.challenge.presentation.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.brewdog.beer.challenge.data.model.MethodMashTemp
import com.brewdog.beer.challenge.databinding.ItemMashTempBinding

class MashTempAdapter : ListAdapter<MethodMashTemp, MethodMashTempHolder>
    (DIFF_CALLBACK_METHOD_MASH_TEMP) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MethodMashTempHolder =
        MethodMashTempHolder.from(parent)

    override fun onBindViewHolder(holder: MethodMashTempHolder, position: Int) =
        holder.onBind(getItem(position))
}

class MethodMashTempHolder(
    private val binding: ItemMashTempBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(model: MethodMashTemp) {
        binding.item = model
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): MethodMashTempHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemMashTempBinding.inflate(layoutInflater, parent, false)
            return MethodMashTempHolder(binding)
        }
    }
}

var DIFF_CALLBACK_METHOD_MASH_TEMP: DiffUtil.ItemCallback<MethodMashTemp> =
    object : DiffUtil.ItemCallback<MethodMashTemp>() {

        override fun areItemsTheSame(oldItem: MethodMashTemp, newItem: MethodMashTemp) =
            oldItem.temp.value == newItem.temp.value &&
                    oldItem.temp.unit == newItem.temp.unit

        override fun areContentsTheSame(oldItem: MethodMashTemp, newItem: MethodMashTemp) =
            oldItem == newItem
    }
