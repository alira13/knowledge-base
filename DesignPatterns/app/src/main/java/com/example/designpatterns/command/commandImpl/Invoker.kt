package com.example.designpatterns.command.commandImpl

// Вызывающий команды
interface Invoker<T : Command> {
    fun addCommand(command: Command)
}