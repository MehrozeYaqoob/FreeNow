package com.freenow.task1.data.model

import com.google.gson.annotations.SerializedName

data class VehicleApiModel(
    @SerializedName("poiList")
    val poiList: List<PoiApiModel>
)
