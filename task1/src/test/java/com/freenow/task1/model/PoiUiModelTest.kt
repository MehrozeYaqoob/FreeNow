package com.freenow.task1.model

import com.freenow.task1.VehicleTestData
import junit.framework.TestCase
import org.junit.Test
import kotlin.test.assertNotEquals

class PoiUiModelTest : TestCase(){

    @Test
    fun `test Vehicle Id`(){
        val vehicle = VehicleTestData.providePoiUiModel()
        assertEquals(439670, vehicle.id)
    }

    @Test
    fun `test Vehicle fleetType`(){
        val vehicle = VehicleTestData.providePoiUiModel()
        assertEquals("POOLING", vehicle.fleetType)
    }

    @Test
    fun `test Vehicle heading`(){
        val vehicle = VehicleTestData.providePoiUiModel()
        assertEquals(344.19529122029735F, vehicle.heading)
    }

    @Test
    fun `test Vehicle coordinate`(){
        val vehicle = VehicleTestData.providePoiUiModel()
        assertEquals(VehicleTestData.provideCoordinate(), vehicle.coordinate)
    }
}
