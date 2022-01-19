package com.example.condorchallenge.repo

import com.example.condorchallenge.repo.model.Team
import com.example.condorchallenge.repo.model.TeamEvent

interface TeamRepository {
    suspend fun getAllTeams(country : String): List<Team>
    suspend fun getTeamEvents(id : String): List<TeamEvent>
}