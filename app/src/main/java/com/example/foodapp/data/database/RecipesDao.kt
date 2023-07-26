package com.example.foodapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodapp.data.database.entities.FavoritesEntity
import com.example.foodapp.data.database.entities.RecipesEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipesEntity: RecipesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity)

    @Query("select * from recipes_table order by id asc")
    fun readRecipes(): Flow<List<RecipesEntity>>

    @Query("select * from favorite_recipes_table order by id asc")
    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>>

    @Delete
    suspend fun deleteFavoriteRecipe(favoriteEntity: FavoritesEntity)

    @Query("delete from favorite_recipes_table")
    suspend fun deleteAllFavoriteRecipes()
}