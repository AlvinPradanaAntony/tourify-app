package com.devcode.tourifyapp.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devcode.tourifyapp.data.remote.repository.DummyDataRepository
import com.devcode.tourifyapp.data.remote.repository.UserRepository
import com.devcode.tourifyapp.di.Injection
import com.devcode.tourifyapp.ui.home.HomeViewModel
import com.devcode.tourifyapp.ui.login.LoginViewModel

class ViewModelFactoryForDummy private constructor(private val dummyDataRepository: DummyDataRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(dummyDataRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactoryForDummy? = null
        fun getInstance(context: Context): ViewModelFactoryForDummy =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactoryForDummy(
                    Injection.provideDummyDataRepository()
                )
            }.also { instance = it }
    }
}
