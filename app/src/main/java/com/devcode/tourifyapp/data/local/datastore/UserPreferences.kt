package com.devcode.tourifyapp.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.devcode.tourifyapp.data.model.UserPreferencesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {

    private val name = stringPreferencesKey("name")
    private val token = stringPreferencesKey("token")

    fun getUserPreferences(): Flow<UserPreferencesModel> {
        return dataStore.data.map { preferences ->
            UserPreferencesModel(
                name = preferences[name] ?: "",
                token = preferences[token] ?: ""
            )
        }
    }

    suspend fun saveUserPreferences(data: UserPreferencesModel) {
        dataStore.edit { preferences ->
            preferences[name] = data.name
            preferences[token] = data.token
        }
    }

    suspend fun clearUserPreferences(){
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}