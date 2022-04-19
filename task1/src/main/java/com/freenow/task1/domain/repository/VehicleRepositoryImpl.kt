package com.freenow.task1.domain.repository

import com.freenow.task1.data.api.ApiHelper
import com.freenow.task1.domain.mapper.VehicleDomainMapper
import com.freenow.task1.presentation.model.VehicleUiModel

class VehicleRepositoryImpl(
    private val apiHelper: ApiHelper,
    private val vehicleDomainMapper: VehicleDomainMapper,
) : VehicleRepository {
    override suspend fun fetchVehicleData(
        p1Lat: Double,
        p1Lon: Double,
        p2Lat: Double,
        p2Lon: Double
    ): VehicleUiModel {
        return apiHelper.fetchVehicleData(p1Lat, p1Lon, p2Lat, p2Lon).let { vehicleDomainMapper.map(it.body()) }
    }
}
