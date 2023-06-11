package com.devcode.tourifyapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class ReviewsResponse(
    @field:SerializedName("user_id")
    val user_id: Int,

    @field:SerializedName("tourist_id")
    val tourist_id: Int,

    @field:SerializedName("score")
    val score: Double,

    @field:SerializedName("review")
    val review: String,

    @field:SerializedName("date")
    val date: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("photo_url")
    val photo_url: String
)
