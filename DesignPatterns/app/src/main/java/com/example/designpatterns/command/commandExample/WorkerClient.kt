package com.example.designpatterns.command.commandExample

import com.example.designpatterns.command.commandExample.worker.WorkInvoker
import com.example.designpatterns.command.commandExample.worker.WorkerCommands
import com.example.designpatterns.observer.mutableObservable.UserLogger
import kotlin.random.Random

// Client - просто вызывает методы Invoker
class WorkerClient {
    fun work() {
        val repository = UserRepositoryReceiver()

        val userLogger1 = UserLogger("LOGGER1")
        repository.observableData.addOnDataChangeListener(userLogger1)

        val userIds = mutableListOf<Int>(0, 10)

        while (true) {
            val id = Random.nextInt(userIds.min(), userIds.max())
            val newUser = "User$id"

            if (userIds.contains(id))
                WorkInvoker.addCommand(WorkerCommands.DeleteUser(repository, newUser))
            else
                WorkInvoker.addCommand(WorkerCommands.AddUser(repository, newUser))
            Thread.sleep(3000)
            userIds.add(id)
        }
    }
}

fun main() {
    WorkerClient().work()
}

