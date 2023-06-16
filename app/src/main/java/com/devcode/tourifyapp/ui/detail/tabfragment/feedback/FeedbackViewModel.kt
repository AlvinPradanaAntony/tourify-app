package com.devcode.tourifyapp.ui.detail.tabfragment.feedback

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.devcode.tourifyapp.data.local.datastore.UserPreference
import com.devcode.tourifyapp.data.model.UserPreferencesModel
import com.devcode.tourifyapp.data.remote.response.ReviewsResponse
import com.devcode.tourifyapp.data.remote.response.TravelDataDummyResponse
import com.devcode.tourifyapp.data.repository.DummyDataRepository

class FeedbackViewModel(
    private val repository: DummyDataRepository,
    private val userPreference: UserPreference
): ViewModel() {
    fun getAllData(): List<ReviewsResponse> = repository.getAllDataReviews()

    fun addReview(data: ReviewsResponse) = repository.addReviews(data)

    fun getUserPreferences(): LiveData<UserPreferencesModel> {
        val pref =  userPreference.getUserPreferences()
        return pref.asLiveData()
    }
}