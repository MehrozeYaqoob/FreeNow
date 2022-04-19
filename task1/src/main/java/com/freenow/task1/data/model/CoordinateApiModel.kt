package com.freenow.task1.data.model

import com.google.gson.annotations.SerializedName

data class CoordinateApiModel(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)

