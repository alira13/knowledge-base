package com.example.copyMethod

class Employee(val id: Int, val name: String) {
    private val _tasks = mutableListOf<Task>()
    val tasks
        get() = _tasks.toList()

    private val _archivedTasks =
        mutableListOf<Task>()
    val archivedTasks: List<Task>
        get() = _archivedTasks.toList()

    fun addTask(task: Task) {
        _tasks.add(task)
        println("Добавлена задача: ${task.title} для сотрудника $name.")
    }

    fun removeTask(taskId: Int) {
        val task = _tasks.find { it.id == taskId }
        if (task != null) {
            _tasks.remove(task)
            _archivedTasks.add(task)
            println("Задача ${task.title} удалена.")
        } else {
            println("Задача с ID $taskId не найдена.")
        }
    }

    fun updateTaskStatus(taskId: Int, newStatus: String) {
        val task = _tasks.find { it.id == taskId }
        if (task != null) {
            _tasks.remove(task)
            _archivedTasks.add(task)
            _tasks.add(task.copy(status = newStatus))
            println("Статус задачи ${task.title} изменен на '$newStatus'.")
        } else {
            println("Задача с ID $taskId не найдена.")
        }
    }

    fun changeTaskAssignee(taskId: Int, newAssignee: String) {
        val task = _tasks.find { it.id == taskId }
        if (task != null) {
            _tasks.remove(task)
            _archivedTasks.add(task)
            _tasks.add(task.copy(assignedTo = newAssignee))
            println("Задача ${task.title} переназначена на $newAssignee.")
        } else {
            println("Задача с ID $taskId не найдена.")
        }
    }

    fun updateTaskPriority(taskId: Int, newPriority: String) {
        val task = _tasks.find { it.id == taskId }
        if (task != null) {
            _tasks.remove(task)
            _archivedTasks.add(task)
            _tasks.add(task.copy(priority = newPriority))
            println("Приоритет задачи ${task.title} изменен на '$newPriority'.")
        } else {
            println("Задача с ID $taskId не найдена.")
        }
    }

    fun modifyTaskDetails(taskId: Int, newTitle: String, newDescription: String) {
        val task = _tasks.find { it.id == taskId }
        if (task != null) {
            _tasks.remove(task)
            _archivedTasks.add(task)
            _tasks.add(task.copy(title = newTitle, description = newDescription))
            println("Детали задачи ${task.id} обновлены.")
        } else {
            println("Задача с ID $taskId не найдена.")
        }
    }

    fun printTasks() {
        println("Список задач для сотрудника $name:")
        _tasks.forEach { it.printTaskInfo() }
    }
}
