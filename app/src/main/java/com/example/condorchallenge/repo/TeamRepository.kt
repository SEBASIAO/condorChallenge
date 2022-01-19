package com.example.condorchallenge.repo

import com.example.condorchallenge.repo.model.Team

interface TeamRepository {
    suspend fun getAllSpainTeams(): List<Team>

}