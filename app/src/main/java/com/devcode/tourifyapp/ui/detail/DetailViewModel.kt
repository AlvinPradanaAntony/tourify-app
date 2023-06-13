package com.devcode.tourifyapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devcode.tourifyapp.data.local.entity.DestinationEntity
import com.devcode.tourifyapp.data.repository.DestinationRepository
import kotlinx.coroutines.launch

class DetailViewModel(
    private val destinationRepository: DestinationRepository
) : ViewModel(){

    private val _isExists = MutableLiveData<DestinationEntity>()
    val isExists: LiveData<DestinationEntity> = _isExists

    fun getDetailDestination(id: String) = destinationRepository.getDetailDestination(id)

    fun addFavorite(data: DestinationEntity) {
        viewModelScope.launch {
            destinationRepository.addFavorite(data)
        }
    }

    fun deleteFavorite(data: DestinationEntity) {
        viewModelScope.launch {
            destinationRepository.deleteFavorite(data)
        }
    }

    fun checkFavorite(id: String) {
        viewModelScope.launch {
            _isExists.value = destinationRepository.getById(id)
        }
    }
}