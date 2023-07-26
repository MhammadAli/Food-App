package com.example.foodapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodapp.models.Result
import com.example.foodapp.util.Constants.FAVORITE_RECIPES_TABLE

@Entity(tableName = FAVORITE_RECIPES_TABLE)
data class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val result: Result
)
