package com.devcode.tourifyapp.data.model

import com.google.gson.annotations.SerializedName

data class UserPreferencesModel (
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("token")
    val token: String,
)