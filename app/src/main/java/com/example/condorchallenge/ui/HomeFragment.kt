package com.example.condorchallenge.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.condorchallenge.MainActivity
import com.example.condorchallenge.R
import com.example.condorchallenge.Resource
import com.example.condorchallenge.databinding.FragmentHomeBinding
import com.example.condorchallenge.repo.model.Team
import com.example.condorchallenge.viewModels.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home){

    private lateinit var binding: FragmentHomeBinding
    private lateinit var mAdapter: TeamsAdapter
    private lateinit var mLayoutManager : LinearLayoutManager

    private val viewModel : HomeFragmentViewModel by viewModels()

    private var teamsList = mutableListOf<Team>()
    private var mActivity: MainActivity? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        mAdapter = TeamsAdapter(teamsList)
        mLayoutManager = LinearLayoutManager(requireContext())
        binding.rvTeams.apply {
            setHasFixedSize(true)
            layoutManager = mLayoutManager
            adapter = mAdapter
        }

        setupActionBar(getString(R.string.app_name))
        getAllSpainTeams()
    }

    private fun getAllSpainTeams() {
        teamsList.clear()
        viewModel.getAllSpainTeams().observe(viewLifecycleOwner, { result ->
            when(result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    teamsList.addAll(result.data)
                    mAdapter.setTeams(teamsList)
                    binding.progressBar.visibility = View.GONE
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        setupActionBar(getString(R.string.app_name))
    }

    private fun setupActionBar(title: String) {
        mActivity = activity as? MainActivity
        mActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mActivity?.supportActionBar?.title = title
    }
}