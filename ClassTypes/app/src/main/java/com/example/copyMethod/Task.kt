package com.example.copyMethod

// Task.kt
class Task(
    val id: Int,
    var title: String,
    var description: String,
    var assignedTo: String,
    var status: String,
    var priority: String
) {
    fun printTaskInfo() {
        println("Задача #$id: $title")
        println("Описание: $description")
        println("Назначена: $assignedTo")
        println("Статус: $status")
        println("Приоритет: $priority")
        println()
    }
}


