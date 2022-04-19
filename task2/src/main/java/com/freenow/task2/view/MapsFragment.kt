package com.freenow.task2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.freenow.task1.presentation.viewmodel.VehicleViewModel
import com.freenow.task1.presentation.viewmodel.ViewModelFactory
import com.freenow.task2.R
import com.freenow.task2.viewmodel.MapViewModelFactory
import com.freenow.task2.viewmodel.MapsViewModel
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import org.koin.android.ext.android.get

class MapsFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance(): MapsFragment {
            return MapsFragment()
        }
    }

    private lateinit var mapsViewModel: MapsViewModel
    private lateinit var vehicleViewModel: VehicleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onDefineViewModel()
        onInitMap()
    }

    /* Init ViewModel using ViewModel.Factory methods */
    private fun onDefineViewModel() {
        mapsViewModel = ViewModelProvider(
            this,
            get<MapViewModelFactory>()
        )[MapsViewModel::class.java]

        vehicleViewModel = ViewModelProvider(
            this,
            get<ViewModelFactory>()
        )[VehicleViewModel::class.java]
    }

    /* Init Maps using with a callback */
    private fun onInitMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    /* Callback to handle maps and showing vehicles */
    private val callback = OnMapReadyCallback { googleMap ->
        vehicleViewModel.vehicleListLiveDataViewState.observe(viewLifecycleOwner) {
            it?.data?.let { vehicleUiModel ->
                context?.let { it1 -> mapsViewModel.showVehicles(vehicleUiModel, googleMap, it1) }
            }
        }
    }

}




