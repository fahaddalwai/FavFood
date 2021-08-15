package com.example.favfoodroom.viewfavfood

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.favfoodroom.database.Food
import com.example.favfoodroom.databinding.FoodListItemBinding

class ViewAllAdapter() : ListAdapter<Food, ViewAllAdapter.ViewHolder>(SleepNightDiffCallback()) {

    class ViewHolder private constructor(val binding: FoodListItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Food) {
            binding.food = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FoodListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }



}


class SleepNightDiffCallback : DiffUtil.ItemCallback<Food>() {

    override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem == newItem
    }
}

