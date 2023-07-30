package com.example.foodapp.ui.fragments.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.adapters.FavoriteRecipesAdapter
import com.example.foodapp.databinding.FragmentFavoriteRecipesBinding
import com.example.foodapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteRecipesFragment : Fragment() {
    lateinit var binding: FragmentFavoriteRecipesBinding
    private val adapter: FavoriteRecipesAdapter by lazy { FavoriteRecipesAdapter() }
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteRecipesBinding.inflate(inflater, container, false)

        setupRecyclerView()
        mainViewModel.readFavoriteRecipes.observe(viewLifecycleOwner){favoritesEntity->
            adapter.submitList(favoritesEntity)
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.apply {
            favoriteRecipesRecyclerView.adapter = adapter
            favoriteRecipesRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        }
    }

}