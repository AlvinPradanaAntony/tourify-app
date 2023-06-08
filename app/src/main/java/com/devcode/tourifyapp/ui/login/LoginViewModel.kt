package com.devcode.tourifyapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devcode.tourifyapp.data.local.datastore.UserPreference
import com.devcode.tourifyapp.data.model.UserPreferencesModel
import com.devcode.tourifyapp.data.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userRepository: UserRepository,
    private val userPreference: UserPreference
) : ViewModel() {

    fun doLogin(email: String, password: String) = userRepository.doLogin(email, password)

    fun saveUserPreference(data: UserPreferencesModel) {
        viewModelScope.launch {
            userPreference.saveUserPreferences(data)
        }
    }
}