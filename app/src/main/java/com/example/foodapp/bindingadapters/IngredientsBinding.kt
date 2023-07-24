package com.example.foodapp.bindingadapters

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foodapp.R
import com.example.foodapp.adapters.IngredientsAdapter
import com.example.foodapp.models.AnalyzedInstruction
import com.example.foodapp.util.Constants.BASE_IMAGE_URL

class IngredientsBinding {
    companion object {


        @BindingAdapter("loadIngredients")
        @JvmStatic
        fun loadIngredients(recyclerView: RecyclerView, data: List<AnalyzedInstruction>?) {
            if (data != null) {
                val adapter = recyclerView.adapter as IngredientsAdapter
                for (element in data) {
                    for (step in element.steps) {
                        adapter.submitList(step.ingredients)
                    }
                }

            }
        }

        @BindingAdapter("loadIngredientImage")
        @JvmStatic
        fun loadIngredientImage(imageView: ImageView, imageName: String) {
            Log.d("IngredientsBinding", "loadIngredientImage: ${BASE_IMAGE_URL + imageName}")
            imageView.load(BASE_IMAGE_URL + imageName) {
                crossfade(600)
                error(R.drawable.ic_error_placeholder)
            }
        }
    }
}