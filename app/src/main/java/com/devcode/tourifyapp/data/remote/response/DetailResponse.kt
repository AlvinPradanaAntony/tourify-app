package com.devcode.tourifyapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetailResponse(

	@field:SerializedName("data")
	val data: DataDestination,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class DataDestination(

	@field:SerializedName("day_open_start")
	val dayOpenStart: String,

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("User")
	val user: User,

	@field:SerializedName("latitude")
	val latitude: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("time_open_start")
	val timeOpenStart: String,

	@field:SerializedName("TourCategory")
	val tourCategory: TourCategory,

	@field:SerializedName("picture")
	val picture: String,

	@field:SerializedName("rating_score")
	val ratingScore: Int,

	@field:SerializedName("day_open_end")
	val dayOpenEnd: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("tour_category_id")
	val tourCategoryId: String,

	@field:SerializedName("time_open_end")
	val timeOpenEnd: String,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("user_id")
	val userId: String,

	@field:SerializedName("price")
	val price: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("longitude")
	val longitude: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)