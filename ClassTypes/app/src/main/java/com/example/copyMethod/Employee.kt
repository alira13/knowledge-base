package com.example.copyMethod

class Employee(val id: Int, val name: String) {
    var tasks = mutableListOf<Task>()
    var archivedTasks = mutableListOf<Task>() // TODO: Реализовать функциональность архивирования задач

    fun addTask(task: Task) {
        tasks.add(task)
        println("Добавлена задача: ${task.title} для сотрудника $name.")
    }

    fun removeTask(taskId: Int) {
        val task = tasks.find { it.id == taskId }
        if (task != null) {
            tasks.remove(task)
            println("Задача ${task.title} удалена.")
        } else {
            println("Задача с ID $taskId не найдена.")
        }
    }

    fun updateTaskStatus(taskId: Int, newStatus: String) {
        val task = tasks.find { it.id == taskId }
        if (task != null) {
            task.status = newStatus  // Прямое изменение, требующее изменения на использование copy()
            println("Статус задачи ${task.title} изменен на '$newStatus'.")
        } else {
            println("Задача с ID $taskId не найдена.")
        }
    }

    fun changeTaskAssignee(taskId: Int, newAssignee: String) {
        val task = tasks.find { it.id == taskId }
        if (task != null) {
            task.assignedTo = newAssignee  // Прямое изменение, требующее изменения на использование copy()
            println("Задача ${task.title} переназначена на $newAssignee.")
        } else {
            println("Задача с ID $taskId не найдена.")
        }
    }

    fun updateTaskPriority(taskId: Int, newPriority: String) {
        val task = tasks.find { it.id == taskId }
        if (task != null) {
            task.priority = newPriority  // Прямое изменение, требующее изменения на использование copy()
            println("Приоритет задачи ${task.title} изменен на '$newPriority'.")
        } else {
            println("Задача с ID $taskId не найдена.")
        }
    }

    fun modifyTaskDetails(taskId: Int, newTitle: String, newDescription: String) {
        val task = tasks.find { it.id == taskId }
        if (task != null) {
            task.title = newTitle  // Прямое изменение, требующее изменения на использование copy()
            task.description = newDescription  // Прямое изменение, требующее изменения на использование copy()
            println("Детали задачи ${task.id} обновлены.")
        } else {
            println("Задача с ID $taskId не найдена.")
        }
    }

    fun printTasks() {
        println("Список задач для сотрудника $name:")
        tasks.forEach { it.printTaskInfo() }
    }
}
