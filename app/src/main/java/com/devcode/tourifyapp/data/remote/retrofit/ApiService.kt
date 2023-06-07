package com.devcode.tourifyapp.data.remote.retrofit

import com.devcode.tourifyapp.data.model.RegisterResponse
import com.devcode.tourifyapp.data.remote.response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    suspend fun doLogin(
        @Field("username") email: String?,
        @Field("password") password: String?
    ): LoginResponse

    @FormUrlEncoded
    @POST("register")
    suspend fun doRegister(
        @Field("name") name: String?,
        @Field("email") email: String?,
        @Field("username") username: String?,
        @Field("password") password: String?
    ) : RegisterResponse
}