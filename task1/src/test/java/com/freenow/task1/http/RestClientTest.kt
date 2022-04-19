package com.freenow.task1.http

import com.freenow.task1.data.api.ApiService
import com.freenow.task1.data.model.PoiApiModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.nio.charset.StandardCharsets

class RestClientTest {

    private lateinit var service: ApiService

    private lateinit var mockWebServer: MockWebServer

    @Before
    @Throws(IOException::class)
    fun createService() {
        mockWebServer = MockWebServer()

        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .create()
                )
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @After
    @Throws(IOException::class)
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    @Throws(JsonSyntaxException::class)
    suspend fun getListVehiclesSuccess() {
        val fileContent = getFileContentAsString("response.json")
        val listType = object: TypeToken<List<PoiApiModel>>() {}.type

        val vehiclesTOList = Gson().fromJson<List<PoiApiModel>>(fileContent.toString(), listType)
        mockWebServer.enqueue(MockResponse().setBody(fileContent.toString()))

        val testObserver = service.fetchVehicleData(
            Mockito.anyDouble(),
            Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyDouble()
        )

        assert(testObserver.body()?.poiList == vehiclesTOList)
    }


    @Throws(IOException::class)
    private fun getFileContentAsString(fileName: String): String {
        val inputStream =
                javaClass.classLoader.getResourceAsStream("sampledata/$fileName")
        val source = inputStream.source().buffer()
        return source.readString(StandardCharsets.UTF_8)
    }
}
