package com.dicoding.tourifyapp.data.remote.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.devcode.tourifyapp.data.model.LoginDataResponse
import com.dicoding.tourifyapp.data.remote.retrofit.ApiService
import com.dicoding.tourifyapp.utils.Decoder
import com.dicoding.tourifyapp.utils.Result
import com.google.gson.Gson

class UserRepository(
    private val apiService: ApiService,
    private val decoder: Decoder
) {

    fun doLogin(email: String, password: String) : LiveData<Result<LoginDataResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.doLogin(email, password)
            val decoded = decoder.decodeJwt(response.data.token)
            val dataLogin = Gson().fromJson(decoded, LoginDataResponse::class.java)
            emit(Result.Success(dataLogin))
        } catch (e: java.lang.Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }



    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiServices: ApiService,
            decoder: Decoder
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiServices, decoder)
            }.also { instance = it }
    }
}