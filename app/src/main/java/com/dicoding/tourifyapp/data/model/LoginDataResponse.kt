package com.dicoding.tourifyapp.data.model

import com.google.gson.annotations.SerializedName

data class LoginDataResponse(

	@field:SerializedName("role")
	val role: String,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("exp")
	val exp: Int,

	@field:SerializedName("iat")
	val iat: Int
)
