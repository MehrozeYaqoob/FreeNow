package com.freenow.task1.usecase

import com.freenow.task1.VehicleTestData
import com.freenow.task1.domain.repository.VehicleRepository
import com.freenow.task1.domain.usecase.VehicleUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

internal class VehicleUseCaseTest {

    private var repository: VehicleRepository = mock()
    private val useCase = VehicleUseCase(repository)

    @Test
    fun `fetch vehicle details with no errors`() = runTest {
        whenever(
            repository.fetchVehicleData(
                Mockito.anyDouble(),
                Mockito.anyDouble(),
                Mockito.anyDouble(),
                Mockito.anyDouble()
            )
        ).doReturn(
            VehicleTestData.provideVehicleData()
        )
        val testObserver = useCase.run(VehicleTestData.provideVehicleParams())
        assertEquals(testObserver, VehicleTestData.provideVehicleData())
    }
}
