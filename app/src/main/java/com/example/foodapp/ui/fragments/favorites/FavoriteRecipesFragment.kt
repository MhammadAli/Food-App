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
    private var _binding: FragmentFavoriteRecipesBinding? = null
    private val binding
        get() = _binding!!


    private val adapter: FavoriteRecipesAdapter by lazy { FavoriteRecipesAdapter() }
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteRecipesBinding.inflate(inflater, container, false)


        binding.apply {
            lifecycleOwner = this@FavoriteRecipesFragment
            mainViewModel = this@FavoriteRecipesFragment.mainViewModel
            adapter = this@FavoriteRecipesFragment.adapter
        }
        setupRecyclerView()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupRecyclerView() {
        binding.apply {
            favoriteRecipesRecyclerView.adapter = this@FavoriteRecipesFragment.adapter
            favoriteRecipesRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        }
    }

}