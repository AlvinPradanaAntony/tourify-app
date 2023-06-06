package com.devcode.tourifyapp.ui.home

import androidx.lifecycle.ViewModel
import com.devcode.tourifyapp.data.remote.repository.DummyDataRepository
import com.devcode.tourifyapp.data.remote.response.TravelDataDummyResponse

class HomeViewModel(private val repository: DummyDataRepository): ViewModel() {
    fun getAllData(): List<TravelDataDummyResponse> = repository.getAllData()
}