package com.freenow.task1.presentation.model

import com.freenow.utils.ViewType

data class VehicleUiModel(
    val poiList: List<PoiUiModel>
) : BaseUiModel {
    override fun getItemViewType(): ViewType {
        return ViewType.VEHICLE
    }
}
