package com.freenow.task1.common

import com.freenow.task1.BuildConfig
import com.freenow.task1.data.api.ApiService
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

internal object ApiClient {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}
