package com.example.foodapp.ui.fragments.recipes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.foodapp.MainViewModel
import com.example.foodapp.adapters.RecipesAdapter
import com.example.foodapp.databinding.FragmentRecipesBinding
import com.example.foodapp.util.Constants.API_KEY
import com.example.foodapp.util.NetworkResult
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment() {
    private lateinit var binding: FragmentRecipesBinding
    private lateinit var mainViewModel: MainViewModel

    private val adapter by lazy { RecipesAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRecipesBinding.inflate(inflater, container, false)

        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
        setupRecyclerView()
        requestApiData()


        return binding.root
    }

    private fun requestApiData() {
        mainViewModel.getRecipes(applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let {
                        mainViewModel.recipesList.value = it.results
                        Log.d(
                            "RecipesFragment",
                            "requestApiData: ${mainViewModel.recipesList.value}"
                        )
                    }
                }

                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    Snackbar.make(binding.root, response.message.toString(), Snackbar.LENGTH_SHORT)
                        .show()
                }

                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        }
    }

    private fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries["number"] = "50"
        queries["apiKey"] = API_KEY
        queries["type"] = "snack"
        queries["diet"] = "vegan"
        queries["addRecipeInformation"] = "true"
        queries["fillIngredient"] = "true"

        return queries
    }

    private fun setupRecyclerView() {

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        showShimmerEffect()
    }

    private fun showShimmerEffect() = binding.recyclerView.showShimmer()
    private fun hideShimmerEffect() = binding.recyclerView.hideShimmer()

}