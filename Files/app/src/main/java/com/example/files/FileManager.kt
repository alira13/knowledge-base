package com.example.files

import java.io.File

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