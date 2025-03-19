package com.example.designpatterns.command.commandImpl

// каким именно образом мы будем выполнять команды. Вытаскивать из очереди или по каким-то условиям
interface Invoker<T : Command> {
    fun executeCommand(command: T)
}