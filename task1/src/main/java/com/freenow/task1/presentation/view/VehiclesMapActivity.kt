package com.freenow.task1.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.freenow.task1.R
import com.freenow.task1.databinding.ActivityMapsBinding
import com.freenow.task1.presentation.model.PoiUiModel
import com.freenow.task1.presentation.viewmodel.VehicleViewModel
import com.freenow.task1.presentation.viewmodel.ViewModelFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import org.koin.android.ext.android.get

class VehiclesMapActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        const val CLICKED_VEHICLE = "CLICKED_VEHICLE"
        const val ZOOM_MAP_LEVEL = 17f
    }

    private var poiUiModel: PoiUiModel? = null
    private lateinit var vehicleViewModel: VehicleViewModel
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent?.extras?.getParcelable<PoiUiModel>(CLICKED_VEHICLE)?.let {
            poiUiModel = it
        }
        onDefineViewModel()
        onInitMap()
    }


    /* Init ViewModel using ViewModel.Factory methods */
    private fun onDefineViewModel() {
        vehicleViewModel = ViewModelProvider(
            this,
            get<ViewModelFactory>()
        )[VehicleViewModel::class.java]
    }

    /* Init Maps using with a callback */
    private fun onInitMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        vehicleViewModel.onMapReady(this, map, poiUiModel)
    }
}




