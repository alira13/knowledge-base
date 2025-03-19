package com.example.designpatterns.command.commandExample.worker

import com.example.designpatterns.command.commandExample.UserRepositoryReceiver
import com.example.designpatterns.command.commandImpl.Command

sealed interface WorkerCommands : Command {
    data class AddUser(val repository: UserRepositoryReceiver, val userName: String) : Command {
        override fun execute() {
            repository.addUser(userName)
        }
    }

    data class DeleteUser(val repository: UserRepositoryReceiver, val userName: String) : Command {
        override fun execute() {
            repository.removeUser(userName)
        }
    }
}