package com.devcode.tourifyapp.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devcode.tourifyapp.data.local.datastore.UserPreference
import com.devcode.tourifyapp.data.repository.DestinationRepository
import com.devcode.tourifyapp.data.repository.DummyDataRepository
import com.devcode.tourifyapp.data.repository.UserRepository
import com.devcode.tourifyapp.di.Injection
import com.devcode.tourifyapp.ui.detail.DetailViewModel
import com.devcode.tourifyapp.ui.explore.ExploreViewModel
import com.devcode.tourifyapp.ui.favourite.FavoriteViewModel
import com.devcode.tourifyapp.ui.home.HomeViewModel
import com.devcode.tourifyapp.ui.login.LoginViewModel
import com.devcode.tourifyapp.ui.register.RegisterViewModel
import com.devcode.tourifyapp.ui.splashscreen.SplashScreenViewModel

class ViewModelFactory private constructor(
    private val userRepository: UserRepository,
    private val destinationRepository: DestinationRepository,
    private val userPreference: UserPreference,
    private val dummyDataRepository: DummyDataRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashScreenViewModel::class.java)) {
            return SplashScreenViewModel(userPreference, userRepository) as T
        } else if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(userRepository, userPreference) as T
        } else if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(userRepository) as T
        } else if (modelClass.isAssignableFrom(ExploreViewModel::class.java)) {
            return ExploreViewModel(destinationRepository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(destinationRepository) as T
        } else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(destinationRepository) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(dummyDataRepository, destinationRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideUserRepository(),
                    Injection.provideDestinationRepository(context),
                    Injection.provideDataStore(context),
                    Injection.provideDummyDataRepository()
                )
            }.also { instance = it }
    }
}
