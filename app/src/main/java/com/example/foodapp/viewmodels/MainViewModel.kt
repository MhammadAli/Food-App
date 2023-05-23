package com.example.foodapp.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.Repository
import com.example.foodapp.models.FoodRecipe
import com.example.foodapp.models.Result
import com.example.foodapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    var recipesResponse: MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()
    val recipesList: MutableLiveData<List<Result>> = MutableLiveData()

    fun getRecipes(quires: Map<String, String>) = viewModelScope.launch {
        getRecipesSafeCall(quires)
    }

    private suspend fun getRecipesSafeCall(quires: Map<String, String>) {
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getRecipes(quires)
                recipesResponse.value = handleFoodRecipesResponse(response)
            } catch (e: Exception) {
                recipesResponse.value = NetworkResult.Error(message = "Recipes not found")
            }
        } else {
            recipesResponse.value = NetworkResult.Error(message = "No internet connection")
        }
    }

    private fun handleFoodRecipesResponse(response: Response<FoodRecipe>): NetworkResult<FoodRecipe> {
        recipesResponse.value = NetworkResult.Loading()
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error(message = "Timeout")
            }

            response.code() == 402 -> {
                return NetworkResult.Error(message = "Api key limited.")
            }

            response.body()!!.results.isEmpty() -> {
                return NetworkResult.Error(message = "Recipes not found")
            }

            response.isSuccessful -> {
                val foodRecipes = response.body()
                return NetworkResult.Success(foodRecipes!!)
            }

            else -> return NetworkResult.Error(message = response.message())
        }
    }


    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false


        return when {
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) -> true // wifi
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_MMS) -> true //  cellular
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_FOTA) -> true // ethernet
            else ->false

        }
    }

}