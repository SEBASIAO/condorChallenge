package com.example.condorchallenge.repo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
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
) : Parcelable


data class TeamResponse(
    @SerializedName("teams")
    val teams: List<Team>
)

data class TeamEventsResponse(
    @SerializedName("results")
    val teams: List<TeamEvent>
)

data class TeamEvent(
    @SerializedName("strEvent")
    val event: String,
    @SerializedName("dateEvent")
    val date: String
)
