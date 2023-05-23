package com.example.foodapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodapp.models.FoodRecipe
import com.example.foodapp.util.Constants.RECIPES_TABLE


@Entity(tableName = RECIPES_TABLE)
data class RecipesEntity(
    val foodRecipe: FoodRecipe,
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0
)