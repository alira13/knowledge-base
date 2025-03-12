package com.example.flowandchannels.stateAsFlow

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Definition(
    @SerialName("definition") val definition: String,
)