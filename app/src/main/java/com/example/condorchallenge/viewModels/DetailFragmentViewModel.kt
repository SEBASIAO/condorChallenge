package com.example.condorchallenge.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.condorchallenge.Resource
import com.example.condorchallenge.repo.TeamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


@HiltViewModel
class DetailFragmentViewModel @Inject constructor(
    private val teamRepository: TeamRepository
) : ViewModel(){

    fun getTeamEvents( id : String) = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(
                Resource.Success(
                    teamRepository.getTeamEvents(id)
                )
            )
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}