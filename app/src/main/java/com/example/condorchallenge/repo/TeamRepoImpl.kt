package com.example.condorchallenge.repo

import com.example.condorchallenge.InternetCheck
import com.example.condorchallenge.repo.model.Team
import com.example.condorchallenge.repo.model.TeamEvent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamRepoImpl @Inject constructor(
    private val apiClient: ApiClient
) : TeamRepository  {
    override suspend fun getAllTeams(country : String): List<Team> {
        return if(InternetCheck.isNetworkAvailable()){
                apiClient.getAllTeams("Soccer", country).teams
        }else{
            emptyList()
        }
    }

    override suspend fun getTeamEvents(id: String): List<TeamEvent> {
        return if(InternetCheck.isNetworkAvailable()){
            apiClient.getTeamEvents(id).teams
        }else{
            emptyList()
        }
    }

}