package com.example.condorchallenge.repo

import com.example.condorchallenge.repo.model.Team
import com.example.condorchallenge.repo.model.TeamResponse
import retrofit2.http.GET

interface ApiClient {

    @GET("search_all_teams.php?s=Soccer&c=Spain")
    suspend fun getAllSpainTeams(): TeamResponse

}