package com.freenow.task1.presentation.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoordinateUiModel(
    val latitude: Double,
    val longitude: Double
) : Parcelable

