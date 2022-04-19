package com.freenow.task1.domain.mapper

import com.freenow.task1.common.Mapper
import com.freenow.task1.data.model.CoordinateApiModel
import com.freenow.task1.data.model.PoiApiModel
import com.freenow.task1.presentation.model.VehicleUiModel
import com.freenow.task1.data.model.VehicleApiModel
import com.freenow.task1.presentation.model.CoordinateUiModel
import com.freenow.task1.presentation.model.PoiUiModel

class VehicleDomainMapper : Mapper<VehicleApiModel, VehicleUiModel> {

    override fun map(from: VehicleApiModel?): VehicleUiModel {
        return VehicleUiModel(
            poiList = from?.poiList?.map { toPoiUiModel(it) } ?: emptyList()
        )
    }

    private fun toPoiUiModel(poiApiModel: PoiApiModel) = PoiUiModel(
        id = poiApiModel.id,
        coordinate = toCoordinateUiModel(poiApiModel.coordinate),
        fleetType = poiApiModel.fleetType,
        heading = poiApiModel.heading
    )

    private fun toCoordinateUiModel(coordinateApiModel: CoordinateApiModel) = CoordinateUiModel(
        latitude = coordinateApiModel.latitude,
        longitude = coordinateApiModel.longitude
    )
}
