package com.freenow.task2.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.freenow.task2.R
import com.freenow.task1.presentation.model.VehicleUiModel
import com.freenow.utils.Utils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*

class MapsViewModel() : ViewModel() {

    fun showVehicles(vehicleDetails: VehicleUiModel, googleMap: GoogleMap, context: Context) {
        val mp = MarkerOptions()
        vehicleDetails.poiList.forEach { poi ->
            val location = LatLng(poi.coordinate.latitude, poi.coordinate.longitude)
            mp.position(location)
            googleMap.addMarker(
                mp.title(poi.id.toString())
                    .icon(
                        Utils.bitmapDescriptorFromVector(
                            context,
                            R.drawable.ic_taxi_side_view,
                            R.drawable.ic_pin_location
                        )?.let {
                            BitmapDescriptorFactory.fromBitmap(
                                it
                            )
                        }
                    )
            )
        }
        drawVehiclesOnMap(googleMap, vehicleDetails)
    }

    private fun drawVehiclesOnMap(googleMap: GoogleMap, vehicleDetails: VehicleUiModel) {
        val builder = LatLngBounds.Builder()
        vehicleDetails.poiList.forEach { poi ->
            builder.include(LatLng(poi.coordinate.latitude, poi.coordinate.longitude))
        }
        val bounds = builder.build()
        val padding = 0
        val cu = CameraUpdateFactory.newLatLngBounds(bounds, padding)
        googleMap.animateCamera(cu)
    }
}


