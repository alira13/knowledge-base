package com.example.designpatterns.command.commandImpl

// любая команда, которую должен выполнить invoker
fun interface Command {
    fun execute()
}
