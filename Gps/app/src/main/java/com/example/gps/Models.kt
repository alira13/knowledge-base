package com.example.gps

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    @SerialName("gps") val gps: GpsDto,
    @SerialName("meta") val meta: MetaDto
)

@Serializable
data class GpsDto(
    @SerialName("truth") val truth: Boolean,
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double,
    @SerialName("speed") val speed: Float,
    @SerialName("direction") val direction: Int
)

@Serializable
data class MetaDto(
    @SerialName("os") val os: String,
    @SerialName("version") val version: String,
    @SerialName("brand") val brand: String,
    @SerialName("ramTotal") val ramTotal: Int,
    @SerialName("ramUsed") val ramUsed: Int,
    @SerialName("memoryTotal") val memoryTotal: Long,
    @SerialName("memoryUsed") val memoryUsed: Long,
    @SerialName("batteryCharge") val batteryCharge: Float,
    @SerialName("appVersion") val appVersion: String
)

fun GpsModel.toDto(): GpsDto {
    return GpsDto(
        truth = this.truth,
        latitude = this.latitude,
        longitude = this.longitude,
        speed = this.speed,
        direction = this.direction
    )
}

fun MetaModel.toDto() : MetaDto {
    return MetaDto(
        os = this.os,
        version = this.version,
        brand = this.brand,
        ramTotal = this.ramTotal,
        ramUsed = this.ramUsed,
        memoryTotal = this.memoryTotal,
        memoryUsed = this.memoryUsed,
        batteryCharge = this.batteryCharge,
        appVersion = this.appVersion
    )
}

fun LocationModel.toDto() : LocationDto {
    return LocationDto(
        gps = this.gps.toDto(),
        meta = this.meta.toDto()
    )
}

data class LocationModel(
    val gps: GpsModel,
    val meta: MetaModel
)

data class GpsModel(
    val truth: Boolean = true,
    val latitude: Double = 0.0,
    val longitude: Double= 0.0,
    val speed: Float = 0f,
    val direction: Int = 0
)

data class MetaModel(
    val os: String = "",
    val version: String= "",
    val brand: String= "",
    val ramTotal: Int = 0,
    val ramUsed: Int = 0,
    val memoryTotal: Long= 0L,
    val memoryUsed: Long = 0L,
    val batteryCharge: Float = 0f,
    val appVersion: String= ""
)


