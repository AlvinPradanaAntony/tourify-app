package com.devcode.tourifyapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.devcode.tourifyapp.data.local.entity.DestinationEntity
import com.devcode.tourifyapp.data.local.room.DestinationDao
import com.devcode.tourifyapp.data.remote.response.DestinationResponse
import com.devcode.tourifyapp.data.remote.response.DetailResponse
import com.devcode.tourifyapp.data.remote.response.RecomendationResponse
import com.devcode.tourifyapp.data.remote.response.SearchResponse
import com.devcode.tourifyapp.data.remote.retrofit.ApiService
import com.devcode.tourifyapp.utils.Result

class DestinationRepository(
    private val apiService: ApiService,
    private val apiMachineLearning: ApiService,
    private val destinationDao: DestinationDao
) {

    fun getAllDestination() : LiveData<Result<DestinationResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getDestination()
            emit(Result.Success(response))
        } catch (e: java.lang.Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun searchDestination(name: String) : LiveData<Result<SearchResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getSearchDestination(name)
            emit(Result.Success(response))
        } catch (e: java.lang.Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getDetailDestination(id: String) : LiveData<Result<DetailResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getDetailDestination(id)
            emit(Result.Success(response))
        } catch (e: java.lang.Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getRecomendation() : LiveData<Result<RecomendationResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiMachineLearning.getRecomendation()
            emit(Result.Success(response))
        } catch (e: java.lang.Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    suspend fun addFavorite(data: DestinationEntity) = destinationDao.addFavorite(data)

    fun getFavorite() : LiveData<List<DestinationEntity>> = destinationDao.getFavorite()

    suspend fun getById(id: String) : DestinationEntity = destinationDao.getById(id)

    suspend fun deleteFavorite(data: DestinationEntity) = destinationDao.deleteFavorite(data)

    companion object {
        @Volatile
        private var instance: DestinationRepository? = null
        fun getInstance(
            apiServices: ApiService,
            apiMachineLearning: ApiService,
            destinationDao: DestinationDao
        ): DestinationRepository =
            instance ?: synchronized(this) {
                instance ?: DestinationRepository(apiServices, apiMachineLearning, destinationDao)
            }.also { instance = it }
    }
}