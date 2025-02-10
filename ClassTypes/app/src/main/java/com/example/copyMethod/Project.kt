package com.example.copyMethod

// Project.kt
class Project(val name: String) {
    private val employees = mutableListOf<Employee>()

    fun addEmployee(employee: Employee) {
        employees.add(employee)
        println("Сотрудник ${employee.name} добавлен в проект '$name'.")
    }

    fun assignTaskToEmployee(employeeId: Int, task: Task) {
        val employee = employees.find { it.id == employeeId }
        if (employee != null) {
            employee.addTask(task)
        } else {
            println("Сотрудник с ID $employeeId не найден.")
        }
    }

    fun updateEmployeeTaskStatus(employeeId: Int, taskId: Int, newStatus: String) {
        val employee = employees.find { it.id == employeeId }
        if (employee != null) {
            employee.updateTaskStatus(taskId, newStatus)
        } else {
            println("Сотрудник с ID $employeeId не найден.")
        }
    }

    fun changeEmployeeTaskAssignee(employeeId: Int, taskId: Int, newAssignee: String) {
        val employee = employees.find { it.id == employeeId }
        if (employee != null) {
            employee.changeTaskAssignee(taskId, newAssignee)
        } else {
            println("Сотрудник с ID $employeeId не найден.")
        }
    }

    fun updateEmployeeTaskPriority(employeeId: Int, taskId: Int, newPriority: String) {
        val employee = employees.find { it.id == employeeId }
        if (employee != null) {
            employee.updateTaskPriority(taskId, newPriority)
        } else {
            println("Сотрудник с ID $employeeId не найден.")
        }
    }

    fun modifyEmployeeTaskDetails(employeeId: Int, taskId: Int, newTitle: String, newDescription: String) {
        val employee = employees.find { it.id == employeeId }
        if (employee != null) {
            employee.modifyTaskDetails(taskId, newTitle, newDescription)
        } else {
            println("Сотрудник с ID $employeeId не найден.")
        }
    }

    fun printAllTasks() {
        println("Все задачи проекта '$name':")
        employees.forEach { it.printTasks() }
    }
}