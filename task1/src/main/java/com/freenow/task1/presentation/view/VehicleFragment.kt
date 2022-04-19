package com.freenow.task1.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.freenow.task1.R
import com.freenow.task1.databinding.ActivityVehicleBinding
import com.freenow.task1.presentation.adapter.VehicleAdapter
import com.freenow.task1.presentation.contract.VehicleView
import com.freenow.task1.presentation.model.PoiUiModel
import com.freenow.task1.presentation.model.VehicleUiModel
import com.freenow.task1.presentation.viewmodel.VehicleViewModel
import com.freenow.task1.presentation.viewmodel.ViewModelFactory
import com.freenow.utils.Status
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.get

class VehicleFragment : Fragment(), VehicleView {

    companion object {
        @JvmStatic
        fun newInstance(): VehicleFragment {
            return VehicleFragment()
        }
    }

    private lateinit var binding: ActivityVehicleBinding
    private lateinit var vehicleViewModel: VehicleViewModel
    private lateinit var adapter: VehicleAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = ActivityVehicleBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitUI()
        onDefineViewModel()
        onObserveLiveData()
    }

    /* Init Recyclerview and set adapter with empty list*/
    private fun onInitUI() {
        val recyclerView = binding.vehicleRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = VehicleAdapter(arrayListOf()).apply {
            onClickAction = {
                showVehicleOnMap(it)
            }
        }
        recyclerView.adapter = adapter
    }

    /* Init ViewModel using ViewModel.Factory methods since, we are passing dependency to view model*/
    private fun onDefineViewModel() {
        vehicleViewModel = ViewModelProvider(
            this,
            get<ViewModelFactory>()
        )[VehicleViewModel::class.java]
    }

    /* Lets observe for any event from view model and act accordingly*/
    private fun onObserveLiveData() {

        vehicleViewModel.vehicleListLiveDataViewState.observe(
            viewLifecycleOwner
        ) { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    response.data?.let { vehicles -> onAdapterItemAdded(vehicles) }
                    hideLoading()
                }
                Status.LOADING -> {
                    showLoading()
                }
                Status.ERROR -> {
                    hideLoading()
                    showSnackBar(response.message ?: getString(R.string.unable_to_load_data))
                }
            }
        }


    }

    private fun showVehicleOnMap(poiUiModel: PoiUiModel){
        val intent = Intent(activity, VehiclesMapActivity::class.java)
        intent.putExtra(VehiclesMapActivity.CLICKED_VEHICLE, poiUiModel)
        startActivity(intent)
    }

    /* List of vehicles are received from remote, lets pass it to adapter*/
    private fun onAdapterItemAdded(vehicles: VehicleUiModel) {
        adapter.apply {
            addVehicles(vehicles)
        }
    }

    /* Oops we have an error, lets notify user about it */
    override fun showSnackBar(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG).show()
    }

    /* Content is being fetched from remote, lets show some loading */
    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.vehicleRecyclerView.visibility = View.GONE
    }

    /* Loading is stopped since we have received some response */
    override fun hideLoading() {
        binding.vehicleRecyclerView.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }
}
