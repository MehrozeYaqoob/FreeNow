package com.freenow.task1.model

import com.freenow.task1.VehicleTestData
import junit.framework.TestCase
import org.junit.Test
import kotlin.test.assertNotEquals

class VehicleUiModelTest : TestCase(){

    @Test
    fun `test Vehicle List `(){
        val vehicleData = VehicleTestData.provideVehicleData()
        assertEquals(VehicleTestData.providePoiUiModel(), vehicleData.poiList[0])
    }
}
