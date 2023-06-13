package com.devcode.tourifyapp.ui.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.devcode.tourifyapp.data.local.datastore.UserPreference
import com.devcode.tourifyapp.data.model.UserPreferencesModel
import com.devcode.tourifyapp.data.repository.UserRepository

class SplashScreenViewModel(
    private val userPreference: UserPreference,
    private val userRepository: UserRepository
) : ViewModel() {

    fun getUserPreferences(): LiveData<UserPreferencesModel> {
        val pref =  userPreference.getUserPreferences()
        return pref.asLiveData()
    }

    fun setToken(token: String) = userRepository.setToken(token)
}