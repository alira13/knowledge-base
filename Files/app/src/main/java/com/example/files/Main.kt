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
