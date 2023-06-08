package com.devcode.tourifyapp.data.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TravelDataDummyResponse(
    val image : Int,
    val name : String,
    val detail : String,
    val isFavorite: Boolean = false,
): Parcelable
