package com.example.condorchallenge.repo

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.condorchallenge.repo.model.Team
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(AndroidJUnit4::class)
class TeamRepoImplTest : TestCase() {

    lateinit var service: ApiClient

    @Before
    public override fun setUp() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(ApiClient::class.java)
    }

    @Test
    fun getAllTeams() {
        val actualTeams = runBlocking {
            service.getAllTeams("Soccer","Spain")
        }

        assertTrue(actualTeams.teams.isNotEmpty())
    }

    @Test
    fun getTeamEvents() {
        val actualEvents = runBlocking {
            service.getTeamEvents("133602")
        }

        assertTrue(actualEvents.events.isNotEmpty())
    }
}