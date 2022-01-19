package com.example.condorchallenge.repo.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("idTeam")
    val id: String = "",
    @SerializedName("strTeam")
    val name: String = "",
    @SerializedName("intFormedYear")
    val foundationYear: String = "",
    @SerializedName("strStadium")
    val stadiumName: String = "",
    @SerializedName("strTeamBadge")
    val badge: String = "",
    @SerializedName("strTeamJersey")
    val jersey: String = "",
    @SerializedName("strDescriptionEN")
    var desc: String = "",
    @SerializedName("strWebsite")
    val web: String = "",
)

data class TeamResponse(
    @SerializedName("teams")
    val teams: List<Team>
)
