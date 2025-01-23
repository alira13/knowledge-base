package com.example.files

import java.io.File

fun main() {
    val fileManager = FileManager(file = File("text.txt"))

    val operations = OperationCode.entries
    while (true) {
        println("Введите код операции:")
        for ((index, op) in operations.withIndex()) {
            println("$index - $op")
        }
        val operationIndex = readln().toInt()

        when (operations[operationIndex]) {
            OperationCode.EXIT -> break
            OperationCode.WRITE -> fileManager.write()
            OperationCode.READ -> fileManager.read()
        }
    }
}

class FileManager(val file: File) {

    fun write() {
        println("Введите задачу или 0 для выхода")
        while (true) {
            val task = readln()
            if (task == "0") break
            file.appendText("$task\n")
        }
    }

    fun read() {
        println("В вашем файле:")
        val str = file.readText().trim()
        val tasks = str.split('\n')
        for ((index, task) in tasks.withIndex()) {
            println("$index) $task")
        }
    }
}