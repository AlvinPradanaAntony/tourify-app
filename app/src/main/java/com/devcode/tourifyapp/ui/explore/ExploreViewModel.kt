package com.devcode.tourifyapp.ui.explore

import androidx.lifecycle.ViewModel
import com.devcode.tourifyapp.data.repository.DestinationRepository

class ExploreViewModel(
    private val destinationRepository: DestinationRepository
) : ViewModel() {

    fun getAllDestination() = destinationRepository.getAllDestination()

    fun searchDestination(name: String) = destinationRepository.searchDestination(name)
}