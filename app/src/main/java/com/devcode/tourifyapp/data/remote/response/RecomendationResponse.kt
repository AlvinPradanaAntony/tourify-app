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
	val harga: Int,

	@field:SerializedName("rating")
	val rating: Any,

	@field:SerializedName("id")
	val id: Int
)
