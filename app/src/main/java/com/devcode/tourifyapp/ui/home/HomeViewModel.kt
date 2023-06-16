package com.devcode.tourifyapp.ui.home

import androidx.lifecycle.ViewModel
import com.devcode.tourifyapp.data.remote.response.TravelBanner
import com.devcode.tourifyapp.data.repository.DummyDataRepository
import com.devcode.tourifyapp.data.remote.response.TravelDataDummyResponse

class HomeViewModel(private val repository: DummyDataRepository): ViewModel() {
    fun getAllData(): List<TravelDataDummyResponse> = repository.getAllData()
    fun getAllDataBanner(): List<TravelBanner> = repository.getAllDataBanner()
}