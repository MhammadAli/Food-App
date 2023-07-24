package com.example.foodapp.ui.fragments.instructions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentInstructionsBinding
import com.example.foodapp.models.Result
import com.example.foodapp.util.Constants.RECIPE_BUNDLE_KEY


class InstructionsFragment : Fragment() {
    lateinit var binding: FragmentInstructionsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInstructionsBinding.inflate(inflater, container, false)

        val args = arguments
        val myBundle: Result? = args?.getParcelable(RECIPE_BUNDLE_KEY)

        binding.instructionsWebView.webViewClient = object : WebViewClient(){}

        binding.instructionsWebView.loadUrl(myBundle!!.sourceUrl)
        return binding.root
    }

}