package com.example.getterSetter

import getterSetter.Worker

fun main() {
    val worker = Worker()

    println("Enter new worker name")
    val name = readln()
    worker.name = name

    println("Enter worker age")
    val age = readln().toInt()
    worker.age = age

    if (worker.name.isNotBlank()) {
        println("Enter new salary")
        val salary = readln().toInt()
        worker.setSalary(salary)
        println("${worker.name} is ${worker.age} years old and his/her salary is ${worker.getSalary()}")
    }
}