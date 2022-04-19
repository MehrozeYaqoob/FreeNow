package com.freenow.task1.data.model

import com.google.gson.annotations.SerializedName

data class PoiApiModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("coordinate")
    val coordinate: CoordinateApiModel,
    @SerializedName("fleetType")
    val fleetType: String,
    @SerializedName("heading")
    val heading: Float
)
