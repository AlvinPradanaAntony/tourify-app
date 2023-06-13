package com.devcode.tourifyapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.devcode.tourifyapp.data.model.LoginDataResponse
import com.devcode.tourifyapp.data.model.RegisterResponse
import com.devcode.tourifyapp.data.model.UserPreferencesModel
import com.devcode.tourifyapp.data.remote.retrofit.ApiConfig
import com.devcode.tourifyapp.data.remote.retrofit.ApiService
import com.devcode.tourifyapp.utils.Decoder
import com.devcode.tourifyapp.utils.Result
import com.google.gson.Gson

class UserRepository(
    private val apiService: ApiService,
    private val decoder: Decoder,
    private val apiConfig: ApiConfig
) {

    fun doLogin(email: String, password: String) : LiveData<Result<UserPreferencesModel>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.doLogin(email, password)
            val decoded = decoder.decodeJwt(response.data.token)
            val dataUser = Gson().fromJson(decoded, LoginDataResponse::class.java)

            setToken(response.data.token)
            emit(Result.Success(UserPreferencesModel(dataUser.name, response.data.token)))
        } catch (e: java.lang.Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun doRegister(name: String, email: String, username: String, password: String) : LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.doRegister(name, email, username, password)
            emit(Result.Success(response))
        } catch (e: java.lang.Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun setToken(token: String) = apiConfig.setToken(token)

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiServices: ApiService,
            decoder: Decoder,
            apiConfig: ApiConfig
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiServices, decoder, apiConfig)
            }.also { instance = it }
    }
}