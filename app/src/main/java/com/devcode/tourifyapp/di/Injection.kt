package com.devcode.tourifyapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.devcode.tourifyapp.data.local.datastore.UserPreference
import com.devcode.tourifyapp.data.local.room.DestinationDao
import com.devcode.tourifyapp.data.local.room.DestinationDatabase
import com.devcode.tourifyapp.data.repository.DummyDataRepository
import com.devcode.tourifyapp.data.repository.UserRepository
import com.devcode.tourifyapp.data.remote.retrofit.ApiConfig
import com.devcode.tourifyapp.data.repository.DestinationRepository
import com.devcode.tourifyapp.utils.Decoder

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")
object Injection {

    fun provideUserRepository(): UserRepository {
        val apiService = ApiConfig.getApiService()
        val decoder = Decoder()
        val apiConfig = ApiConfig
        return UserRepository.getInstance(apiService, decoder, apiConfig)
    }

    fun provideDestinationRepository(context: Context): DestinationRepository {
        val apiService = ApiConfig.getApiService()
        val apiMachineLearning = ApiConfig.getApiServiceMl()
        val database = DestinationDatabase.getInstance(context)
        val dao = database.destinationDao()
        return DestinationRepository.getInstance(apiService, apiMachineLearning, dao)
    }

    fun provideDataStore(context: Context): UserPreference {
        val dataStore = context.dataStore
        return  UserPreference.getInstance(dataStore)
    }

    fun provideDummyDataRepository(): DummyDataRepository {
        return DummyDataRepository.getInstance()
    }
}