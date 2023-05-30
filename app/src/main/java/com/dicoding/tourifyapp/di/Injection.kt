package com.dicoding.tourifyapp.di

import com.dicoding.tourifyapp.data.remote.repository.UserRepository
import com.dicoding.tourifyapp.data.remote.retrofit.ApiConfig
import com.dicoding.tourifyapp.utils.Decoder

object Injection {

    fun provideUserRepository(): UserRepository {
        val apiService = ApiConfig.getApiService()
        val decoder = Decoder()
        return UserRepository.getInstance(apiService, decoder)
    }

}