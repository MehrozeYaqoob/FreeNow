package com.freenow.task1.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.freenow.task1.domain.usecase.VehicleUseCase

class ViewModelFactory(private val restaurantUseCase: VehicleUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VehicleViewModel::class.java)) {
            return VehicleViewModel(restaurantUseCase) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
