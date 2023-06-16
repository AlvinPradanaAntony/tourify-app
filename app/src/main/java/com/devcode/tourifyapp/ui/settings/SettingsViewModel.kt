package com.devcode.tourifyapp.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.devcode.tourifyapp.data.local.datastore.UserPreference
import com.devcode.tourifyapp.data.model.UserPreferencesModel
import com.devcode.tourifyapp.utils.ThemesPreferences
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val pref: ThemesPreferences,
    private val userPreference: UserPreference,
) : ViewModel() {
    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }

    fun doLogout() {
        viewModelScope.launch {
            userPreference.clearUserPreferences()
        }
    }

    fun getUserPreferences(): LiveData<UserPreferencesModel> {
        val pref =  userPreference.getUserPreferences()
        return pref.asLiveData()
    }
}