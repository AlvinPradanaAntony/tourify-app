package com.devcode.tourifyapp.ui.home

import androidx.lifecycle.ViewModel
import com.devcode.tourifyapp.data.repository.DummyDataRepository
import com.devcode.tourifyapp.data.remote.response.TravelDataDummyResponse
import com.devcode.tourifyapp.data.repository.DestinationRepository

class HomeViewModel(
    private val repository: DummyDataRepository,
    private val destinationRepository: DestinationRepository
    ): ViewModel() {
    fun getAllData(): List<TravelDataDummyResponse> = repository.getAllData()

    fun getRecomendation() = destinationRepository.getRecomendation()
}