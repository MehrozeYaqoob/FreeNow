package com.freenow.task1.repository

import com.freenow.task1.VehicleTestData
import com.freenow.task1.data.api.ApiHelper
import com.freenow.task1.data.model.VehicleApiModel
import com.freenow.task1.domain.mapper.VehicleDomainMapper
import com.freenow.task1.domain.repository.VehicleRepository
import com.freenow.task1.domain.repository.VehicleRepositoryImpl
import com.freenow.task1.domain.usecase.VehicleUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext.get
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
internal class VehicleRepositoryImplTest {

    @Mock
    private lateinit var vehicleDomainMapper: VehicleDomainMapper
    @Mock
    private lateinit var apiHelper: ApiHelper

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `verify fetch vehicle details return correct values`() = runTest {
        val expectedResult = VehicleTestData.provideVehicleData()
        whenever(vehicleDomainMapper.map(VehicleTestData.provideVehicleApiData()))
            .doReturn(VehicleTestData.provideVehicleData())
        whenever(apiHelper.fetchVehicleData(Mockito.anyDouble(),Mockito.anyDouble(),Mockito.anyDouble(),Mockito.anyDouble())).doReturn(
            Response.success(VehicleTestData.provideVehicleApiData())
        )
        val repository = VehicleRepositoryImpl(
            apiHelper,
            vehicleDomainMapper
        )
        val actualResult = repository.fetchVehicleData(Mockito.anyDouble(),Mockito.anyDouble(),Mockito.anyDouble(),Mockito.anyDouble())
        assertEquals(actualResult, expectedResult)
    }
}
