package com.example.condorchallenge.repo

import com.example.condorchallenge.repo.model.Team
import com.example.condorchallenge.repo.model.TeamEventsResponse
import com.example.condorchallenge.repo.model.TeamResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {

    @GET("search_all_teams.php")
    suspend fun getAllTeams(@Query("s") sport : String, @Query("c") country : String): TeamResponse

    @GET("eventslast.php")
    suspend fun getTeamEvents(@Query("id") id : String) : TeamEventsResponse

}