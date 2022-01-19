package com.example.condorchallenge.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.condorchallenge.databinding.TeamItemBinding
import com.example.condorchallenge.repo.model.Team

class TeamsAdapter(
    private var list: List<Team>
) : RecyclerView.Adapter<TeamsAdapter.TeamHolder>(){

    fun setTeams(teams: MutableList<Team>) {
        list = teams
        notifyDataSetChanged()
    }

    class TeamHolder(private val binding: TeamItemBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(team: Team){
            with(binding){
                Glide.with(context).load(team.badge).fitCenter().into(imageViewTeamBadge)
                tvName.text = team.name
                tvStadium.text = team.stadiumName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamHolder {
        val binding = TeamItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: TeamHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}