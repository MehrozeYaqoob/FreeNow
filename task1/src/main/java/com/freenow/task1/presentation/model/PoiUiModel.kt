package com.freenow.task1.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PoiUiModel(
    val id: Int,
    val coordinate: CoordinateUiModel,
    val fleetType: String,
    val heading: Float
) : Parcelable
