package com.freenow.task1.model

import com.freenow.task1.VehicleTestData
import junit.framework.TestCase
import org.junit.Test
import kotlin.test.assertNotEquals

class CoordinateUiModelTest : TestCase(){

    @Test
    fun `test latitude`(){
        val coordinate = VehicleTestData.provideCoordinate()
        assertEquals(53.46036882190762, coordinate.latitude)
    }

    @Test
    fun `test longitude`(){
        val coordinate = VehicleTestData.provideCoordinate()
        assertEquals(9.909716434648558, coordinate.longitude)
    }

}
