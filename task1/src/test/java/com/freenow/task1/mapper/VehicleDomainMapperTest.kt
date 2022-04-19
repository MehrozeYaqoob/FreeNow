package com.freenow.task1.mapper

import com.freenow.task1.VehicleTestData
import com.freenow.task1.domain.mapper.VehicleDomainMapper
import org.junit.Test
import kotlin.test.assertEquals

class VehicleDomainMapperTest {

    private val mapper = VehicleDomainMapper()

    @Test
    fun `verify mappers is mapping to correct data`() {
        val params = VehicleTestData.provideVehicleApiData()
        with(mapper.map(params)) {
            assertEquals(VehicleTestData.provideVehicleData(), this)
            assertEquals(VehicleTestData.provideVehicleData().poiList, this.poiList)
        }
    }
}
