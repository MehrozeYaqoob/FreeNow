package com.freenow.task1.domain.repository

import com.freenow.task1.presentation.model.VehicleUiModel

interface VehicleRepository {
    suspend fun fetchVehicleData(p1Lat: Double,
                                 p1Lon: Double,
                                 p2Lat: Double,
                                 p2Lon: Double): VehicleUiModel
}
