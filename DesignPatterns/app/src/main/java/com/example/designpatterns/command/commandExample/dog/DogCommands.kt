package com.example.designpatterns.command.commandExample.dog

import com.example.designpatterns.command.commandImpl.Command

sealed interface DogCommands : Command {
    class RunCommand : Command {
        override fun execute() {
            println("Say run")
        }
    }

    class StopCommand : Command {
        override fun execute() {
            println("Say stop")
        }
    }
}