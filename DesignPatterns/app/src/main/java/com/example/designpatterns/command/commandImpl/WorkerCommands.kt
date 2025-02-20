package com.example.designpatterns.command.commandImpl

sealed interface WorkerCommands : Command {
    data class AddUser(val repository: UserRepository, val userName: String) : Command {
        override fun execute() {
            repository.addUser(userName)
        }
    }

    data class DeleteUser(val repository: UserRepository, val userName: String) : Command {
        override fun execute() {
            repository.removeUser(userName)
        }
    }
}