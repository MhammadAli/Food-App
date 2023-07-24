package com.example.foodapp.ui.fragments.ingredients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.adapters.IngredientsAdapter
import com.example.foodapp.adapters.RecipesAdapter
import com.example.foodapp.databinding.FragmentIngredientsBinding
import com.example.foodapp.models.Result
import com.example.foodapp.util.Constants
import com.example.foodapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IngredientsFragment : Fragment() {

    lateinit var binding: FragmentIngredientsBinding
    private lateinit var mainViewModel: MainViewModel
    private val adapter by lazy { IngredientsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIngredientsBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
        setupRecyclerView()

        val args = arguments
        val myBundle: Result? = args?.getParcelable(Constants.RECIPE_BUNDLE_KEY)

        mainViewModel.ingredientsList.value = myBundle?.analyzedInstructions


        return binding.root
    }

    private fun setupRecyclerView() {

        binding.ingredientsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.ingredientsRecyclerView.adapter = adapter
    }

}