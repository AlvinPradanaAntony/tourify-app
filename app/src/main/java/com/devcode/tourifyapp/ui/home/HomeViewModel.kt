package com.devcode.tourifyapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.devcode.tourifyapp.data.local.datastore.UserPreference
import com.devcode.tourifyapp.data.model.UserPreferencesModel
import com.devcode.tourifyapp.data.remote.response.TravelBanner
import com.devcode.tourifyapp.data.repository.DummyDataRepository
import com.devcode.tourifyapp.data.remote.response.TravelDataDummyResponse
import com.devcode.tourifyapp.data.repository.DestinationRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: DummyDataRepository,
    private val destinationRepository: DestinationRepository,
    private val userPreference: UserPreference
    ): ViewModel() {
    fun getAllData(): List<TravelDataDummyResponse> = repository.getAllData()

    fun getRecomendation() = destinationRepository.getRecomendation()

    fun getUserPreferences(): LiveData<UserPreferencesModel> {
        val pref =  userPreference.getUserPreferences()
        return pref.asLiveData()
    }
    fun getAllDataBanner(): List<TravelBanner> = repository.getAllDataBanner()

}