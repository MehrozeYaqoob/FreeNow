package com.freenow.task1.data.api

import com.freenow.task1.data.model.VehicleApiModel
import retrofit2.Response

class ApiHelper(private val apiService: ApiService) {
    suspend fun fetchVehicleData(
        p1Lat: Double,
        p1Lon: Double,
        p2Lat: Double,
        p2Lon: Double
    ): Response<VehicleApiModel> = apiService.fetchVehicleData(p1Lat, p1Lon, p2Lat, p2Lon)
}
