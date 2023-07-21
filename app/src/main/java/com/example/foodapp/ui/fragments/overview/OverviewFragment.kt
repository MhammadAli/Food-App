package com.example.foodapp.ui.fragments.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import coil.load
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentOverviewBinding
import com.example.foodapp.databinding.FragmentRecipesBinding
import com.example.foodapp.models.Result


class OverviewFragment : Fragment() {
    lateinit var binding: FragmentOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOverviewBinding.inflate(inflater, container, false)

        val args = arguments
        val myBundle: Result? = args?.getParcelable("recipeBundle")

        binding.apply {
            mainImageView.load(myBundle?.image)
            titleTextView.text = myBundle?.title
            likesTextView.text = myBundle?.aggregateLikes.toString()
            timeTexView.text = myBundle?.readyInMinutes.toString()
            summaryTextView.text = myBundle?.summary
        }



        if (myBundle?.vegetarian == true) {
            binding.apply {
                vegetarianImageView.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )

                vegetarianTextView.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )

            }
        }

        if (myBundle?.vegan == true) {
            binding.apply {
                veganImageView.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )

                veganTextView.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )

            }
        }

        if (myBundle?.glutenFree == true) {
            binding.apply {
                glutenFreeImageView.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )

                glutenFreeTextView.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )

            }
        }

        if (myBundle?.dairyFree == true) {
            binding.apply {
                dairyFreeImageView.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )

                dairyFreeTextView.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )

            }
        }

        if (myBundle?.veryHealthy == true) {
            binding.apply {
                healthyImageView.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )

                healthyTexView.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )

            }
        }

        if (myBundle?.cheap == true) {
            binding.apply {
                cheapImageView.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )

                cheapTextView.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )

            }
        }



        return binding.root
    }

}