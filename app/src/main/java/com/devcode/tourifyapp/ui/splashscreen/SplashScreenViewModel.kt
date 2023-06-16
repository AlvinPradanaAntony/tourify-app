package com.devcode.tourifyapp.ui.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.devcode.tourifyapp.data.local.datastore.UserPreference
import com.devcode.tourifyapp.data.model.UserPreferencesModel
import com.devcode.tourifyapp.data.repository.UserRepository
import com.devcode.tourifyapp.utils.ThemesPreferences

class SplashScreenViewModel(
    private val userPreference: UserPreference,
    private val userRepository: UserRepository,
    private val themesPreferences: ThemesPreferences
) : ViewModel() {

    fun getUserPreferences(): LiveData<UserPreferencesModel> {
        val pref =  userPreference.getUserPreferences()
        return pref.asLiveData()
    }

    fun getThemeSettings(): LiveData<Boolean> {
        return themesPreferences.getThemeSetting().asLiveData()
    }

    fun setToken(token: String) = userRepository.setToken(token)
}