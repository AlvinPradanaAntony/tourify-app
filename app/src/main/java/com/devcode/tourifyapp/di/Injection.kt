package com.devcode.tourifyapp.di

import com.devcode.tourifyapp.data.remote.repository.UserRepository
import com.devcode.tourifyapp.data.remote.retrofit.ApiConfig
import com.devcode.tourifyapp.utils.Decoder

object Injection {

    fun provideUserRepository(): UserRepository {
        val apiService = ApiConfig.getApiService()
        val decoder = Decoder()
        return UserRepository.getInstance(apiService, decoder)
    }

}