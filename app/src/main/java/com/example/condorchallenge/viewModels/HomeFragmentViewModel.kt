package com.example.condorchallenge.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.condorchallenge.Resource
import com.example.condorchallenge.repo.TeamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val teamRepository: TeamRepository
) : ViewModel(){

    fun getAllSpainTeams() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(
                Resource.Success(
                    teamRepository.getAllSpainTeams()
                )
            )
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}