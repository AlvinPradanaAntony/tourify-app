package com.devcode.tourifyapp.data.repository

import com.devcode.tourifyapp.data.remote.response.DummyData
import com.devcode.tourifyapp.data.remote.response.ReviewsResponse
import com.devcode.tourifyapp.data.remote.response.TravelBanner
import com.devcode.tourifyapp.data.remote.response.TravelDataDummyResponse

class DummyDataRepository {

    fun getAllData(): List<TravelDataDummyResponse> = DummyData.dummyDataTravel
    fun getAllDataBanner(): List<TravelBanner> = DummyData.dummyBanner
    fun getAllDataReviews(): List<ReviewsResponse> = DummyData.dummyDataReviews

    fun addReviews(data: ReviewsResponse) = DummyData.dummyDataReviews.add(data)

    companion object {
        @Volatile
        private var instance: DummyDataRepository? = null

        fun getInstance(): DummyDataRepository =
            instance ?: synchronized(this) {
                DummyDataRepository().apply {
                    instance = this
                }
            }
    }

}