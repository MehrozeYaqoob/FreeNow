package com.freenow.task1.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freenow.task1.R
import com.freenow.task1.common.exceptionHandler
import com.freenow.task1.domain.usecase.VehicleUseCase
import com.freenow.task1.presentation.model.PoiUiModel
import com.freenow.task1.presentation.model.VehicleUiModel
import com.freenow.task1.presentation.view.VehiclesMapActivity
import com.freenow.utils.Resource
import com.freenow.utils.Utils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.launch

class VehicleViewModel(
    private val vehicleUseCase: VehicleUseCase,
) : ViewModel() {

    private val startPoint = LatLng(53.694865, 9.757589)
    private val endPoint = LatLng(53.394655, 10.099891)

    private val vehicleListLiveData = MutableLiveData<Resource<VehicleUiModel>>()
    val vehicleListLiveDataViewState: LiveData<Resource<VehicleUiModel>>
        get() = vehicleListLiveData

    init {
        fetchVehicles()
    }

    private fun fetchVehicles() {
        vehicleListLiveData.value = Resource.loading(null)

        val errorHandler = viewModelScope.exceptionHandler {
            handleVehicleListError(it)
        }

        viewModelScope.launch(errorHandler) {
            handleOnSuccess(vehicleUseCase.run(VehicleUseCase.Params(startPoint, endPoint)))
        }
    }

    private fun handleOnSuccess(vehicles: VehicleUiModel) {
        vehicleListLiveData.value = Resource.success(vehicles)
    }

    private fun handleVehicleListError(throwable: Throwable? = null) {
        vehicleListLiveData.value = Resource.error(throwable?.localizedMessage, null)
    }

    fun onMapReady(context: Context, googleMap: GoogleMap, poiUiModel: PoiUiModel?) {
        poiUiModel?.let { vehicle ->
            googleMap.apply {
                val latLng = LatLng(vehicle.coordinate.latitude, vehicle.coordinate.longitude)
                moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, VehiclesMapActivity.ZOOM_MAP_LEVEL))
                showVehicle(context, googleMap, vehicle)
            }
        }
    }

    private fun showVehicle(context: Context, googleMap: GoogleMap, vehicle: PoiUiModel) {
        val latLng = LatLng(vehicle.coordinate.latitude, vehicle.coordinate.longitude)
        googleMap.apply {
            val marker = addMarker(MarkerOptions().position(latLng))
            marker?.setIcon(Utils.bitmapDescriptorFromVector(
                context,
                R.drawable.ic_taxi_side_view,
                R.drawable.ic_pin_location
            )?.let {
                BitmapDescriptorFactory.fromBitmap(
                    it
                )
            })
            marker?.title = vehicle.id.toString()
        }
    }
}
