package com.example.condorchallenge.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.condorchallenge.MainActivity
import com.example.condorchallenge.R
import com.example.condorchallenge.Resource
import com.example.condorchallenge.databinding.FragmentHomeBinding
import com.example.condorchallenge.repo.model.Team
import com.example.condorchallenge.viewModels.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

const val COUNTRY_SPAIN = "Spain"
private const val COUNTRY_GERMANY = "Germany"
private const val COUNTRY_ENGLAND = "England"


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), TeamsAdapter.OnTeamClickListener{

    private lateinit var binding: FragmentHomeBinding
    private lateinit var mAdapter: TeamsAdapter
    private lateinit var mLayoutManager : LinearLayoutManager

    private val viewModel : HomeFragmentViewModel by viewModels()
    private val args by navArgs<HomeFragmentArgs>()

    private var teamsList = mutableListOf<Team>()
    private var mActivity: MainActivity? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        mAdapter = TeamsAdapter(teamsList, this)
        mLayoutManager = LinearLayoutManager(requireContext())
        binding.rvTeams.apply {
            setHasFixedSize(true)
            layoutManager = mLayoutManager
            adapter = mAdapter
        }
        mAdapter.setTeams(args.teamsList.teams.toMutableList())

        setupActionBar(getString(R.string.app_name))
        selectFilter(binding.tvSpain)
        setUpFilters()
    }

    private fun setUpFilters(){
        with(binding){
            tvEngland.setOnClickListener {
                unSelectFilters()
                getAllTeams(COUNTRY_ENGLAND)
                selectFilter(tvEngland)

            }
            tvGermany.setOnClickListener {
                unSelectFilters()
                getAllTeams(COUNTRY_GERMANY)
                selectFilter(tvGermany)
            }
            tvSpain.setOnClickListener {
                unSelectFilters()
                getAllTeams(COUNTRY_SPAIN)
                selectFilter(tvSpain)
            }
        }
    }

    private fun unSelectFilters(){
        with(binding){
            mActivity?.let {
                tvEngland.setTextColor(it.getColor(R.color.white))
                tvSpain.setTextColor(it.getColor(R.color.white))
                tvGermany.setTextColor(it.getColor(R.color.white))
            }
        }
    }

    private fun selectFilter(textView: TextView){
        mActivity?.let {
        textView.setTextColor(it.getColor(R.color.yellowCondor))
        }
    }

    private fun getAllTeams(country : String) {
        teamsList.clear()
        viewModel.getAllTeams(country).observe(viewLifecycleOwner, { result ->
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

    override fun onTeamClick(team: Team) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(team)
        findNavController().navigate(action)
    }
}