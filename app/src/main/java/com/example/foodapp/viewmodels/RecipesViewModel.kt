package com.example.foodapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.foodapp.util.Constants
import com.example.foodapp.util.Constants.API_KEY
import com.example.foodapp.util.Constants.QUERY_ADD_RECIPE_INFORMATION
import com.example.foodapp.util.Constants.QUERY_API_KEY
import com.example.foodapp.util.Constants.QUERY_DIET
import com.example.foodapp.util.Constants.QUERY_FILL_INGREDIENTS
import com.example.foodapp.util.Constants.QUERY_NUMBER
import com.example.foodapp.util.Constants.QUERY_TYPE

class RecipesViewModel(application: Application) : AndroidViewModel(application) {

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries[QUERY_NUMBER] = "50"
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = "main course"
        queries[QUERY_DIET] = "gluten free"
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }
}