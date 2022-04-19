package com.freenow.task1

import com.freenow.task1.data.model.CoordinateApiModel
import com.freenow.task1.data.model.PoiApiModel
import com.freenow.task1.data.model.VehicleApiModel
import com.freenow.task1.domain.usecase.VehicleUseCase
import com.freenow.task1.presentation.model.CoordinateUiModel
import com.freenow.task1.presentation.model.PoiUiModel
import com.freenow.task1.presentation.model.VehicleUiModel
import com.google.android.gms.maps.model.LatLng


object VehicleTestData {
    private val startPoint = LatLng(53.694865, 9.757589)
    private val endPoint = LatLng(53.394655, 10.099891)

    fun provideVehicleData() = VehicleUiModel(
        listOf(providePoiUiModel())
    )

    fun providePoiUiModel() = PoiUiModel(
        id = 439670,
        provideCoordinate(),
        "POOLING",
        heading = 344.19529122029735F
    )

    fun provideCoordinate() = CoordinateUiModel(latitude = 53.46036882190762, longitude = 9.909716434648558)


    fun provideVehicleApiData() = VehicleApiModel(
        listOf(providePoiApiModel())
    )

    fun providePoiApiModel() = PoiApiModel(
        id = 439670,
        provideApiCoordinate(),
        "POOLING",
        heading = 344.19529122029735F
    )

    fun provideApiCoordinate() = CoordinateApiModel(latitude = 53.46036882190762, longitude = 9.909716434648558)

    fun provideVehicleParams() = VehicleUseCase.Params(startPoint, endPoint)
}
