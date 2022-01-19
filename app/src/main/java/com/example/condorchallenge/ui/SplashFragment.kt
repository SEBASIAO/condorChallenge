package com.example.condorchallenge.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.condorchallenge.R
import com.example.condorchallenge.Resource
import com.example.condorchallenge.databinding.FragmentSplashBinding
import com.example.condorchallenge.repo.model.TeamResponse
import com.example.condorchallenge.viewModels.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var binding: FragmentSplashBinding

    private val viewModel : HomeFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)
        getAllTeams(COUNTRY_SPAIN)
    }

    override fun onResume() {
        super.onResume()
        val supportActionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
        supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        val supportActionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
        supportActionBar?.show()
    }

    private fun getAllTeams(country: String) {
        viewModel.getAllTeams(country).observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    val action = SplashFragmentDirections.actionSplashFragmentToHomeFragment(
                        TeamResponse(
                            result.data
                        )
                    )
                    findNavController().navigate(action)
                }
                is Resource.Failure -> {
                }
            }
        })
    }
}