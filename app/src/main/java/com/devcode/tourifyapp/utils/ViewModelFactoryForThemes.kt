package com.devcode.tourifyapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devcode.tourifyapp.ui.settings.SettingsViewModel

class ViewModelFactoryForThemes(private val pref: ThemesPreferences) : ViewModelProvider.NewInstanceFactory()  {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}