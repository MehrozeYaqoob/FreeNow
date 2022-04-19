package com.freenow.task1.domain.usecase

import com.freenow.task1.presentation.model.VehicleUiModel
import com.freenow.task1.domain.repository.VehicleRepository
import com.google.android.gms.maps.model.LatLng

class VehicleUseCase(
    private val vehicleRepository: VehicleRepository,
) : CoroutineUseCase<VehicleUseCase.Params, VehicleUiModel> {

    override suspend fun run(params: Params): VehicleUiModel {
        return vehicleRepository.fetchVehicleData(
            params.startPoint.latitude,
            params.startPoint.longitude,
            params.endPoint.latitude,
            params.endPoint.longitude
        )
    }

    data class Params(val startPoint: LatLng, val endPoint: LatLng)
}
