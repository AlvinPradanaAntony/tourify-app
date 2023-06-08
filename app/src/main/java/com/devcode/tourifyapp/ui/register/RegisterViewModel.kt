package com.devcode.tourifyapp.ui.register

import androidx.lifecycle.ViewModel
import com.devcode.tourifyapp.data.repository.UserRepository

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun doRegister(name: String, email: String, username: String, password: String) =
        userRepository.doRegister(name, email, username, password)
}