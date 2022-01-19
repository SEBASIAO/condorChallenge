package com.example.condorchallenge.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.condorchallenge.R
import com.example.condorchallenge.databinding.DetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.detail_fragment) {

    private lateinit var binding : DetailFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DetailFragmentBinding.bind(view)
    }
}