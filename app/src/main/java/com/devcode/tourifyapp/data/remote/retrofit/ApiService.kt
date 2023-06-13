package com.devcode.tourifyapp.data.remote.retrofit

import com.devcode.tourifyapp.data.model.RegisterResponse
import com.devcode.tourifyapp.data.remote.response.DestinationResponse
import com.devcode.tourifyapp.data.remote.response.DetailResponse
import com.devcode.tourifyapp.data.remote.response.LoginResponse
import com.devcode.tourifyapp.data.remote.response.SearchResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun doLogin(
        @Field("username") email: String?,
        @Field("password") password: String?
    ): LoginResponse

    @FormUrlEncoded
    @POST("auth/register")
    suspend fun doRegister(
        @Field("name") name: String?,
        @Field("email") email: String?,
        @Field("username") username: String?,
        @Field("password") password: String?
    ) : RegisterResponse

    @GET("tourist-destination")
    suspend fun getDestination(): DestinationResponse

    @GET("tourist-destination/search/{name}")
    suspend fun getSearchDestination(
        @Path("name") name: String
    ) : SearchResponse

    @GET("tourist-destination/{id}")
    suspend fun getDetailDestination(
        @Path("id") id: String
    ) : DetailResponse
}