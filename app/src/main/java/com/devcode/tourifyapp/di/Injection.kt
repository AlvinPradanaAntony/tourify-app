package com.devcode.tourifyapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.devcode.tourifyapp.data.local.datastore.UserPreference
import com.devcode.tourifyapp.data.repository.DummyDataRepository
import com.devcode.tourifyapp.data.repository.UserRepository
import com.devcode.tourifyapp.data.remote.retrofit.ApiConfig
import com.devcode.tourifyapp.utils.Decoder

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")
object Injection {

    fun provideUserRepository(): UserRepository {
        val apiService = ApiConfig.getApiService()
        val decoder = Decoder()
        return UserRepository.getInstance(apiService, decoder)
    }

    fun provideDataStore(context: Context): UserPreference {
        val dataStore = context.dataStore
        return  UserPreference.getInstance(dataStore)
    }

    fun provideDummyDataRepository(): DummyDataRepository {
        return DummyDataRepository.getInstance()
    }

}