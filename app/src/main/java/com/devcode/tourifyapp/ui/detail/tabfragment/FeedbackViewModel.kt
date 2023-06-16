package com.devcode.tourifyapp.ui.detail.tabfragment

import androidx.lifecycle.ViewModel
import com.devcode.tourifyapp.data.remote.response.ReviewsResponse
import com.devcode.tourifyapp.data.remote.response.TravelDataDummyResponse
import com.devcode.tourifyapp.data.repository.DummyDataRepository

class FeedbackViewModel(private val repository: DummyDataRepository): ViewModel() {
    fun getAllData(): List<ReviewsResponse> = repository.getAllDataReviews()
}