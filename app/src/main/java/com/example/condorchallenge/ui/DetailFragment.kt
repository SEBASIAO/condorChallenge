package com.example.condorchallenge.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.condorchallenge.MainActivity
import com.example.condorchallenge.R
import com.example.condorchallenge.Resource
import com.example.condorchallenge.databinding.DetailFragmentBinding
import com.example.condorchallenge.repo.model.Team
import com.example.condorchallenge.repo.model.TeamEvent
import com.example.condorchallenge.viewModels.DetailFragmentViewModel
import com.example.condorchallenge.viewModels.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.detail_fragment) {

    private lateinit var binding : DetailFragmentBinding
    private lateinit var mAdapter: TeamEventsAdapter
    private lateinit var mLayoutManager : LinearLayoutManager

    private val viewModel : DetailFragmentViewModel by viewModels()
    private var eventsList = mutableListOf<TeamEvent>()

    private val args by navArgs<DetailFragmentArgs>()
    private var mActivity: MainActivity? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DetailFragmentBinding.bind(view)
        mAdapter = TeamEventsAdapter(eventsList)
        mLayoutManager = LinearLayoutManager(requireContext())
        binding.rvEvents.apply {
            setHasFixedSize(true)
            layoutManager = mLayoutManager
            adapter = mAdapter
        }
        loadView()
        getTeamEvents()
    }

    private fun loadView() {
        with(binding){
            Glide.with(requireContext()).load(args.team.badge).fitCenter().into(imageViewTeamBadge)
            Glide.with(requireContext()).load(args.team.jersey).fitCenter().into(imageViewTeamJersey)
            tvTeamName.text = args.team.name
            tvTeamDesc.text = args.team.desc
            tvTeamFoundationYear.text = args.team.foundationYear
            if (args.team.web == ""){
                linearLayout4.isVisible = false
            }  else {
                linearLayout4.isVisible = true
                tvTeamWeb.text = args.team.web
            }

            setupActionBar(args.team.name)
        }
    }

    private fun setupActionBar(nickname: String) {
        mActivity = activity as? MainActivity
        mActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mActivity?.supportActionBar?.title = nickname
    }

    private fun getTeamEvents() {
        eventsList.clear()
        viewModel.getTeamEvents(args.team.id).observe(viewLifecycleOwner, { result ->
            when(result) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    eventsList.addAll(result.data)
                    mAdapter.setTeamEvents(eventsList)
                }
                is Resource.Failure -> {
                }
            }
        })
    }
}