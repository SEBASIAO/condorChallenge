package com.example.condorchallenge.repo

import com.example.condorchallenge.InternetCheck
import com.example.condorchallenge.repo.model.Team
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamRepoImpl @Inject constructor(
    private val apiClient: ApiClient
) : TeamRepository  {
    override suspend fun getAllSpainTeams(): List<Team> {
        return if(InternetCheck.isNetworkAvailable()){
                apiClient.getAllSpainTeams().teams
        }else{
            emptyList()
        }
    }

}