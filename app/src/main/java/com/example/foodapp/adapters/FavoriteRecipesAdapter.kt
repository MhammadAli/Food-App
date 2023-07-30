package com.example.foodapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.data.database.entities.FavoritesEntity
import com.example.foodapp.databinding.FavoriteRecipesRowLayoutBinding

class FavoriteRecipesAdapter: ListAdapter<FavoritesEntity, FavoriteRecipesAdapter.MyViewHolder>(DiffCallback) {

    class MyViewHolder(private val binding: FavoriteRecipesRowLayoutBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(favoritesEntity: FavoritesEntity){
            binding.favoritesEntity = favoritesEntity
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavoriteRecipesRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<FavoritesEntity>() {
        override fun areItemsTheSame(oldItem: FavoritesEntity, newItem: FavoritesEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FavoritesEntity, newItem: FavoritesEntity): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentRecipe = getItem(position)
        holder.bind(currentRecipe)
    }
}