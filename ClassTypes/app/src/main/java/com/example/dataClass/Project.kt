package com.example.dataClass

// Класс проекта
data class Project(
    val projectId: Int,
    val projectName: String,
    val client: String,
    val budget: Double,
    val durationMonths: Int
)
