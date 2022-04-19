package com.freenow.task1.di

import com.freenow.task1.BuildConfig
import com.freenow.task1.common.ApiClient
import com.freenow.task1.data.api.ApiHelper
import com.freenow.task1.domain.mapper.VehicleDomainMapper
import com.freenow.task1.domain.repository.VehicleRepositoryImpl
import com.freenow.task1.domain.usecase.VehicleUseCase
import com.freenow.task1.presentation.viewmodel.ViewModelFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val task1Module = module {

    single {
        VehicleDomainMapper()
    }

    single {
        ApiHelper(ApiClient.apiService)
    }

    single {
        VehicleRepositoryImpl(get(), get())
    }

    single {
        VehicleUseCase(get<VehicleRepositoryImpl>())
    }

    single {
        ViewModelFactory(get())
    }
}


