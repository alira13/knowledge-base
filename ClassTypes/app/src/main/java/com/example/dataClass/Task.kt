package com.example.dataClass

// Класс задачи
data class Task(
    val taskId: Int,
    val title: String,
    val description: String,
    val assignee: TeamMember,
    val priority: Int,
    val deadline: String
)