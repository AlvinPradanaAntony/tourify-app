package com.devcode.tourifyapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecomendationResponse(

	@field:SerializedName("data")
	val data: List<RecomItem>
)

data class RecomItem(

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("harga")
	val harga: String,

	@field:SerializedName("rating")
	val rating: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("picture")
	val picture: String
)
