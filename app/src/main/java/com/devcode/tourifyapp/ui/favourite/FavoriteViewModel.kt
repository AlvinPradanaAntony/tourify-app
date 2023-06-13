package com.devcode.tourifyapp.ui.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.devcode.tourifyapp.data.local.entity.DestinationEntity
import com.devcode.tourifyapp.data.repository.DestinationRepository

class FavoriteViewModel(
    destinationRepository: DestinationRepository
) : ViewModel() {

    val listFavorite: LiveData<List<DestinationEntity>> = destinationRepository.getFavorite()
}