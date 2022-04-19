package com.freenow.task1.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freenow.task1.R
import com.freenow.task1.TestSchedulerRule
import com.freenow.task1.VehicleTestData
import com.freenow.task1.common.exceptionHandler
import com.freenow.task1.domain.usecase.VehicleUseCase
import com.freenow.task1.presentation.model.PoiUiModel
import com.freenow.task1.presentation.model.VehicleUiModel
import com.freenow.task1.presentation.view.VehiclesMapActivity
import com.freenow.task1.presentation.viewmodel.VehicleViewModel
import com.freenow.utils.Resource
import com.freenow.utils.Status
import com.freenow.utils.Utils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Response
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
internal class VehicleViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var vehicleUseCase: VehicleUseCase

    @get:Rule
    val testSchedulerRule = TestSchedulerRule()


    private lateinit var viewModel: VehicleViewModel

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var vehicleStateObserver: Observer<Resource<VehicleUiModel>>

    init {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Before
    fun setUp() = runTest {
        coEvery { vehicleUseCase.run(VehicleTestData.provideVehicleParams()) } returns VehicleTestData.provideVehicleData()
        viewModel = VehicleViewModel(vehicleUseCase)
        viewModel.vehicleListLiveDataViewState.observeForever(vehicleStateObserver)

    }

    @ExperimentalCoroutinesApi
    @Test
    fun `verify error on vehicles when requested for load`() {
        coVerify (exactly = 1){ vehicleUseCase.run(VehicleTestData.provideVehicleParams()) }
        testSchedulerRule.testScheduler.triggerActions()
        verify(vehicleStateObserver).onChanged(
            Resource(status= Status.ERROR, data=null, message= Mockito.any())
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `verify success on vehicles when requested for load`() {
        coVerify (exactly = 1){ vehicleUseCase.run(VehicleTestData.provideVehicleParams()) }
        testSchedulerRule.testScheduler.triggerActions()
        verify(vehicleStateObserver).onChanged(
            Resource.success(VehicleTestData.provideVehicleData())
        )
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
        testDispatcher.cleanupTestCoroutines()
    }
}
