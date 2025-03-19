package com.example.designpatterns.command.commandExample

import com.example.designpatterns.command.commandImpl.Command

// Как именно мы будем
interface Invoker<T : Command> {
    fun addCommand(command: Command)
}