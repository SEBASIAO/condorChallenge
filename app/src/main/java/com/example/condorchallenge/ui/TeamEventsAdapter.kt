package com.example.condorchallenge.ui

import android.content.Context
import android.mtp.MtpEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.condorchallenge.databinding.EventItemBinding
import com.example.condorchallenge.databinding.TeamItemBinding
import com.example.condorchallenge.repo.model.Team
import com.example.condorchallenge.repo.model.TeamEvent

class TeamEventsAdapter(
    private var list: List<TeamEvent>,
) : RecyclerView.Adapter<TeamEventsAdapter.TeamEventHolder>(){

    fun setTeamEvents(events: MutableList<TeamEvent>) {
        list = events
        notifyDataSetChanged()
    }

    class TeamEventHolder(private val binding: EventItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: TeamEvent){
            with(binding){
                tvTeamEvent.text = event.event
                tvTeamEventDate.text = event.date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamEventHolder {
        val binding = EventItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamEventHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamEventHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}