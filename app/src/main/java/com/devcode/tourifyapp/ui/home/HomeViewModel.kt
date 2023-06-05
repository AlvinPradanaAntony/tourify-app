package com.devcode.tourifyapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.devcode.tourifyapp.data.remote.repository.DummyDataRepository
import com.devcode.tourifyapp.data.remote.response.TravelDataDummyResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(private val repository: DummyDataRepository): ViewModel() {
    fun getAllData(): List<TravelDataDummyResponse> = repository.getAllData()
}