package com.example.gps.network

import android.util.Log
import com.example.gps.LocationModel
import com.example.gps.toDto
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


object ApiRoutes {
    fun sendMetaInformation(address: String): String = "$address/api/v1/meta"
}


class MetaRepositoryImpl(
    private val api: Api
) {

    companion object{
        const val PATH = "https://morze-dev.fivegen.ru"
    }
    suspend fun sendMetaInformation(data: LocationModel): Boolean {
        Log.d("MY", "sendMetaInformation")
        //val route = appState.serverRoute.getValue()
        val route = PATH

        return try {
            val result = api.postRaw(
                url = ApiRoutes.sendMetaInformation(route),
                body = data.toDto(),
            )
            Log.d("MY", "*** result = ${result.toString()}")
            Log.d("MY", "*****")
            return (result== ApiResult2.Success)
        } catch (th: Throwable) {
            Log.d("MY", ">>>>>>>${th.message.toString()}")
            return false
        }
    }
}
