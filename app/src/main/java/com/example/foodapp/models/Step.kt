package com.example.foodapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Step(
    @SerializedName("ingredients")
    val ingredients: List<Ingredient>,
    @SerializedName("number")
    val number: Int,
    @SerializedName("step")
    val step: String
):Parcelable